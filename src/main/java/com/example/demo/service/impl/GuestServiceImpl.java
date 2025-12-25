package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.exception.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {
    
    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;
    
    public GuestServiceImpl(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public Guest createGuest(String email, String firstName, String lastName, String password, String phoneNumber) {
        if (guestRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        Guest guest = new Guest(email, firstName, lastName, passwordEncoder.encode(password), phoneNumber);
        return guestRepository.save(guest);
    }
    
    @Override
    public Guest createGuest(Guest guest) {
        if (guestRepository.existsByEmail(guest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        return guestRepository.save(guest);
    }
    
    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
    }
    
    @Override
    public Guest updateGuest(Long id, String firstName, String lastName, String phoneNumber) {
        Guest guest = guestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setPhoneNumber(phoneNumber);
        return guestRepository.save(guest);
    }
    
    @Override
    public Guest updateGuest(Long id, Guest update) {
        Guest guest = guestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
        if (update.getFullName() != null) {
            guest.setFullName(update.getFullName());
        }
        if (update.getPhoneNumber() != null) {
            guest.setPhoneNumber(update.getPhoneNumber());
        }
        guest.setVerified(update.isVerified());
        guest.setActive(update.getActive());
        if (update.getRole() != null) {
            guest.setRole(update.getRole());
        }
        return guestRepository.save(guest);
    }
    
    @Override
    public void deactivateGuest(Long id) {
        Guest guest = guestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
        guest.setActive(false);
        guestRepository.save(guest);
    }
    
    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }
}