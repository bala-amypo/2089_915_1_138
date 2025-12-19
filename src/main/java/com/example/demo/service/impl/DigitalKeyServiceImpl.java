package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.DigitalKeyService;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    DigitalKeyRepository digitalKeyRepository;
    RoomBookingRepository roomBookingRepository;

    public DigitalKeyServiceImpl(DigitalKeyRepository digitalKeyRepository,
                                 RoomBookingRepository roomBookingRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
        this.roomBookingRepository = roomBookingRepository;
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
