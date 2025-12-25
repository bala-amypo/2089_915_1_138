package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class KeyShareRequestServiceImpl implements KeyShareRequestService {
    
    private final KeyShareRequestRepository keyShareRequestRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    
    public KeyShareRequestServiceImpl(KeyShareRequestRepository keyShareRequestRepository, 
                                     DigitalKeyRepository digitalKeyRepository, 
                                     GuestRepository guestRepository) {
        this.keyShareRequestRepository = keyShareRequestRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }
    
    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        // Validate dates
        if (request.getShareEnd().before(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after share start");
        }
        
        // Validate users are different
        if (request.getSharedBy().getId().equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be the same");
        }
        
        // Validate digital key exists and is active
        DigitalKey key = digitalKeyRepository.findById(request.getDigitalKey().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Digital key not found"));
        
        if (!key.getActive()) {
            throw new IllegalStateException("Cannot share inactive key");
        }
        
        // Validate guests exist and sharedWith is verified and active
        Guest sharedBy = guestRepository.findById(request.getSharedBy().getId())
            .orElseThrow(() -> new ResourceNotFoundException("SharedBy guest not found"));
        
        Guest sharedWith = guestRepository.findById(request.getSharedWith().getId())
            .orElseThrow(() -> new ResourceNotFoundException("SharedWith guest not found"));
        
        if (!sharedWith.getVerified() || !sharedWith.getActive()) {
            throw new IllegalStateException("SharedWith guest must be verified and active");
        }
        
        return keyShareRequestRepository.save(request);
    }
    
    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {
        KeyShareRequest request = keyShareRequestRepository.findById(requestId)
            .orElseThrow(() -> new ResourceNotFoundException("KeyShareRequest not found with id: " + requestId));
        request.setStatus(status);
        return keyShareRequestRepository.save(request);
    }
    
    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return keyShareRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("KeyShareRequest not found with id: " + id));
    }
    
    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return keyShareRequestRepository.findBySharedById(guestId);
    }
    
    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return keyShareRequestRepository.findBySharedWithId(guestId);
    }
}