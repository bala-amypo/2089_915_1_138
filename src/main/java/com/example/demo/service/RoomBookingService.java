package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoomBooking;

public interface RoomBookingService {
    RoomBooking createBooking(RoomBooking booking);
    RoomBooking updateBooking(Long id, RoomBooking booking);
    RoomBooking getBookingById(Long id);
    List<RoomBooking> getBookingsForGuest(Long guestId);
    void cancelBooking(Long id);
}
