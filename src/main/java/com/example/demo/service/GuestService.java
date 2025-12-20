package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Guest;

public interface GuestService {
    Guest createGuest(Guest guest);
    Guest updateGuest(Long id, Guest guest);
    Guest getGuestById(Long id);
    List<Guest> getAllGuests();
    void deactivateGuest(Long id);
    Optional<Guest> getGuestByEmail(String email);
}
