package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.KeyShareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Transactional
public class KeyShareServiceImpl implements KeyShareService {
    
    private final KeyShareRequestRepository keyShareRequestRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    
    public KeyShareServiceImpl(KeyShareRequestRepository keyShareRequestRepository,
                              DigitalKeyRepository digitalKeyRepository,
                              GuestRepository guestRepository) {
        this.keyShareRequestRepository = keyShareRequestRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }
    
    @Override
    public KeyShareRequest createShareRequest(Long digitalKeyId, Long sharedById, Long sharedWithId, LocalDateTime shareStart, LocalDateTime shareEnd) {
        if (shareEnd.isBefore(shareStart)) {
            throw new IllegalArgumentException("Share end time must be after start time");
        }
        
        if (sharedById.equals(sharedWithId)) {
            throw new IllegalArgumentException("Cannot share with yourself");
        }
        
        DigitalKey digitalKey = digitalKeyRepository.findById(digitalKeyId).orElse(null);
        Guest sharedBy = guestRepository.findById(sharedById).orElse(null);
        Guest sharedWith = guestRepository.findById(sharedWithId).orElse(null);
        
        if (digitalKey == null || sharedBy == null || sharedWith == null) {
            return null;
        }
        
        KeyShareRequest request = new KeyShareRequest(digitalKey, sharedBy, sharedWith, 
                                                     Timestamp.valueOf(shareStart), 
                                                     Timestamp.valueOf(shareEnd));
        return keyShareRequestRepository.save(request);
    }
}