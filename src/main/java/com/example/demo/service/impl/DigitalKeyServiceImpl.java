package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.RoomBooking;
import com.example.demo.model.Guest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.UUID;
import java.util.List;

@Service
@Transactional
public class DigitalKeyServiceImpl implements DigitalKeyService {
    
    private final DigitalKeyRepository digitalKeyRepository;
    private final RoomBookingRepository roomBookingRepository;
    
    public DigitalKeyServiceImpl(DigitalKeyRepository digitalKeyRepository, RoomBookingRepository roomBookingRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
        this.roomBookingRepository = roomBookingRepository;
    }
    
    @Override
    public DigitalKey generateDigitalKey(Long bookingId) {
        RoomBooking booking = roomBookingRepository.findById(bookingId).orElse(null);
        if (booking == null || !booking.isActive()) {
            return null;
        }
        
        String keyValue = UUID.randomUUID().toString();
        DigitalKey digitalKey = new DigitalKey(booking, keyValue);
        return digitalKeyRepository.save(digitalKey);
    }
    
    @Override
    public DigitalKey generateKey(Long bookingId) {
        RoomBooking booking = roomBookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        
        if (!booking.getActive()) {
            throw new IllegalStateException("Cannot generate key for inactive booking");
        }
        
        String keyValue = UUID.randomUUID().toString();
        DigitalKey digitalKey = new DigitalKey();
        digitalKey.setBooking(booking);
        digitalKey.setKeyValue(keyValue);
        digitalKey.setIssuedAt(Instant.now());
        digitalKey.setExpiresAt(Instant.now().plusSeconds(86400)); // 24 hours
        digitalKey.setActive(true);
        
        return digitalKeyRepository.save(digitalKey);
    }
    
    @Override
    public DigitalKey getActiveKeyForBooking(Long bookingId) {
        return digitalKeyRepository.findByBookingIdAndActiveTrue(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("No active key found for booking: " + bookingId));
    }
    
    @Override
    public DigitalKey getKeyById(Long id) {
        return digitalKeyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Digital key not found with id: " + id));
    }
    
    @Override
    public List<DigitalKey> getKeysForGuest(Long guestId) {
        return digitalKeyRepository.findByBookingGuestId(guestId);
    }
    
    @Override
    public List<DigitalKey> getActiveKeysByGuestEmail(String email) {
        return digitalKeyRepository.findActiveKeysByGuestEmail(email);
    }
}