package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    KeyShareRequestRepository keyShareRequestRepository;
    DigitalKeyRepository digitalKeyRepository;
    GuestRepository guestRepository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository keyShareRequestRepository,
                                      DigitalKeyRepository digitalKeyRepository,
                                      GuestRepository guestRepository) {
        this.keyShareRequestRepository = keyShareRequestRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }



    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request){
        return null;
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId,String status){
        return null;
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id){
        return null;
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId){
        return null;
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId){
        return null;
    }
}
