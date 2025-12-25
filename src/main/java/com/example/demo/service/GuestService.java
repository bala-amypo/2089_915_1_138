package com.example.demo.service;

import com.example.demo.model.Guest;
import java.util.List;

public interface GuestService {
    Guest createGuest(String email, String firstName, String lastName, String password, String phoneNumber);
    Guest createGuest(Guest guest);
    Guest getGuestById(Long id);
    Guest updateGuest(Long id, String firstName, String lastName, String phoneNumber);
    Guest updateGuest(Long id, Guest guest);
    void deactivateGuest(Long id);
    List<Guest> getAllGuests();
}