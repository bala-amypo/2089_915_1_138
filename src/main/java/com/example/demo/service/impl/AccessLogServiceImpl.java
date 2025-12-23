package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
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

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessLogServiceImpl implements AccessLogService {
    private final AccessLogRepository accessLogRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    private final KeyShareRequestRepository keyShareRequestRepository;

    public AccessLogServiceImpl(AccessLogRepository accessLogRepository,
                                DigitalKeyRepository digitalKeyRepository,
                                GuestRepository guestRepository,
                                KeyShareRequestRepository keyShareRequestRepository) {
        this.accessLogRepository = accessLogRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
        this.keyShareRequestRepository = keyShareRequestRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {
        log.validateAccessTime();

        DigitalKey digitalKey = digitalKeyRepository.findById(log.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Digital key not found"));
        if (!digitalKey.getActive()) {
            log.setResult("DENIED");
            log.setReason("Digital key is not active");
            return accessLogRepository.save(log);
        }

        Guest guest = guestRepository.findById(log.getGuest().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        if (!guest.getActive()) {
            log.setResult("DENIED");
            log.setReason("Guest account is not active");
            return accessLogRepository.save(log);
        }

        boolean hasValidAccess = false;
        String reason = "";

        if (digitalKey.getBooking().getGuest().getId().equals(guest.getId())) {
            hasValidAccess = true;
            reason = "Guest is the booking owner";
        } else {
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            List<KeyShareRequest> approvedRequests = keyShareRequestRepository.findBySharedWithId(guest.getId()).stream()
                    .filter(req -> req.getDigitalKey().getId().equals(digitalKey.getId()))
                    .filter(req -> req.getStatus().equals("APPROVED"))
                    .filter(req -> req.getShareStart().before(currentTime) && req.getShareEnd().after(currentTime))
                    .collect(Collectors.toList());

            if (!approvedRequests.isEmpty()) {
                hasValidAccess = true;
                reason = "Guest has approved key share request";
            }
        }

        if (hasValidAccess) {
            log.setResult("SUCCESS");
            log.setReason(reason);
        } else {
            log.setResult("DENIED");
            log.setReason("Guest does not have valid access to this digital key");
        }

        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByDigitalKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }
}