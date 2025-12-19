package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;

public class RoomBookingServiceImpl implements RoomBookingService{
    
    RoomBookingRepository roomBookingRepository;

    public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository) {
        this.roomBookingRepository = roomBookingRepository;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking){
        return null;
    }

    @Override
    public RoomBooking updateBooking(Long id,RoomBooking booking){
        return null;
    }

    @Override
    public RoomBooking getBookingById(Long id){
        return null;
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId){
        return null;
    }

    @Override
    public void cancelBooking(Long id){
        
    }
}
