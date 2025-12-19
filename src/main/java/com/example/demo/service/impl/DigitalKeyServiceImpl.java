package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.DigitalKeyService;

public class DigitalKeyServiceImpl implements DigitalKeyService {
    
    DigitalKeyRepository digitalKeyRepository;

    public DigitalKeyServiceImpl(DigitalKeyRepository digitalKeyRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
    }

    @Override
    public DigitalKey generateKey(Long bookingId){
        return null;
    }

    @Override
    public DigitalKey getKeyById(Long id){
        return null;
    }

    @Override
    public DigitalKey getActiveKeyForBooking(Long bookingId){
        return null;
    }

    @Override
    public List<DigitalKey> getKeysForGuest(Long guestId){
        return null;
    }
}
