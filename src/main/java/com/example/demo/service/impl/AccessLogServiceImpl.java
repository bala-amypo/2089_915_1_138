package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class AccessLogServiceImpl implements AccessLogService {
    
    private final AccessLogRepository accessLogRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    private final KeyShareRequestRepository keyShareRequestRepository;
    
    public AccessLogServiceImpl(AccessLogRepository accessLogRepository, DigitalKeyRepository digitalKeyRepository, 
                               GuestRepository guestRepository, KeyShareRequestRepository keyShareRequestRepository) {
        this.accessLogRepository = accessLogRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
        this.keyShareRequestRepository = keyShareRequestRepository;
    }
    
    @Override
    public AccessLog createLog(AccessLog log) {
        // Check if access time is in the future
        if (log.getAccessTime() != null && log.getAccessTime().toInstant().isAfter(Instant.now())) {
            throw new IllegalArgumentException("Access time cannot be in the future");
        }
        
        DigitalKey key = log.getDigitalKey();
        Guest guest = log.getGuest();
        
        // Determine access result
        String result = "DENIED";
        String reason = "Unknown";
        
        if (key != null && key.getActive()) {
            // Check if key is expired
            if (key.getExpiresAt() != null && key.getExpiresAt().isBefore(Instant.now())) {
                reason = "Key expired";
            }
            // Check if key is not yet valid
            else if (key.getIssuedAt() != null && key.getIssuedAt().isAfter(Instant.now())) {
                reason = "Key not yet valid";
            }
            // Check if guest is the booking owner
            else if (key.getBooking() != null && key.getBooking().getGuest() != null && 
                     key.getBooking().getGuest().getId().equals(guest.getId())) {
                result = "SUCCESS";
                reason = "Booking owner";
            }
            // Check if guest has shared access
            else {
                List<KeyShareRequest> shareRequests = keyShareRequestRepository.findBySharedWithId(guest.getId());
                boolean hasValidShare = shareRequests.stream()
                    .anyMatch(req -> req.getDigitalKey().getId().equals(key.getId()) && 
                                   "APPROVED".equals(req.getStatus()) &&
                                   req.getShareStart().toInstant().isBefore(Instant.now()) &&
                                   req.getShareEnd().toInstant().isAfter(Instant.now()));
                
                if (hasValidShare) {
                    result = "SUCCESS";
                    reason = "Shared access";
                } else {
                    reason = "No valid access";
                }
            }
        } else {
            reason = "Inactive key";
        }
        
        log.setResult(result);
        log.setReason(reason);
        
        return accessLogRepository.save(log);
    }
    
    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }
    
    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByDigitalKeyId(keyId);
    }
    
    @Override
    public List<AccessLog> getLogsBetween(Instant start, Instant end) {
        return accessLogRepository.findByAccessTimeBetween(start, end);
    }
}