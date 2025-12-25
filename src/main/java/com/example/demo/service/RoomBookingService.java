package com.example.demo.service;

import com.example.demo.model.RoomBooking;
// import com.example.demo.model.Guest;
import java.time.LocalDate;
import java.util.List;

public interface RoomBookingService {
    RoomBooking createBooking(Long guestId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate);
    RoomBooking createBooking(RoomBooking booking);
    RoomBooking getBookingById(Long id);
    List<RoomBooking> listBookingsForGuest(Long guestId);
    List<RoomBooking> getBookingsForGuest(Long guestId);
    RoomBooking updateBooking(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate);
    RoomBooking updateBooking(Long bookingId, RoomBooking booking);
    void deactivateBooking(Long id);
    void addRoommateToBooking(Long bookingId, Long roommateId);
    void removeRoommateFromBooking(Long bookingId, Long roommateId);
}