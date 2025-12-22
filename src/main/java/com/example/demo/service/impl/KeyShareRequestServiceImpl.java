package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        request.validateShareRequest();

        DigitalKey digitalKey = digitalKeyRepository.findById(request.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Digital key not found"));
        if (!digitalKey.getActive()) {
            throw new IllegalArgumentException("Digital key is not active");
        }

        Guest sharedBy = guestRepository.findById(request.getSharedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Shared by guest not found"));
        Guest sharedWith = guestRepository.findById(request.getSharedWith().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Shared with guest not found"));

        request.setDigitalKey(digitalKey);
        request.setSharedBy(sharedBy);
        request.setSharedWith(sharedWith);
        request.setStatus("PENDING");

        return keyShareRequestRepository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {
        KeyShareRequest request = getShareRequestById(requestId);
        request.setStatus(status);
        return keyShareRequestRepository.save(request);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return keyShareRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key share request not found with id: " + id));
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