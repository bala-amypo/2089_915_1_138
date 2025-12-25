package com.example.demo.service;

import com.example.demo.model.DigitalKey;
import java.util.List;

public interface DigitalKeyService {
    DigitalKey generateDigitalKey(Long bookingId);
    DigitalKey generateKey(Long bookingId);
    DigitalKey getKeyById(Long id);
    DigitalKey getActiveKeyForBooking(Long bookingId);
    List<DigitalKey> getKeysForGuest(Long guestId);
    List<DigitalKey> getActiveKeysByGuestEmail(String email);
}