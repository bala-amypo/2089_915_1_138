package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;

public class GuestServiceImpl implements GuestService {
    GuestRepository guestRepo;

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
    public List<Guest> getAllGuest(){
        return null;
    }

    @Override
    public void deactivateGuest(Long id){

    }
}

