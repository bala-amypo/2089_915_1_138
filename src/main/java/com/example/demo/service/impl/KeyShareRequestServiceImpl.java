package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;

public class KeyShareRequestServiceImpl implements KeyShareRequestService {
    KeyShareRequestRepository keyShareRequestRepository;

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
