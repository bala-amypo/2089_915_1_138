package com.example.demo.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

    GuestRepository guestRepository;
    PasswordEncoder passwordEncoder;

    public GuestServiceImpl(GuestRepository guestRepository,
                            PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Guest createGuest(Guest guest){
        return null;
    }

    @Override
    public Guest updateGuest(Long id,Guest guest){
        return null;
    }

    @Override
    public Guest getGuestById(Long id){
        return null;
    }

    @Override
    public List<Guest> getAllGuests(){
        return null;
    }

    @Override
    public void deactivateGuest(Long id){

    }
}

