package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;

@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {
    DigitalKeyService digitalKeyService;
    
    @PostMapping("/generate/{bookingId}")
    public DigitalKey generate(@PathVariable Long bookingId) {
        return digitalKeyService.generateKey(bookingId);
    }

    @GetMapping("/{id}")
    public DigitalKey getById(@PathVariable Long id) {
        return digitalKeyService.getKeyById(id);
    }

    @GetMapping("/booking/{bookingId}")
    public DigitalKey getActive(@PathVariable Long bookingId) {
        return digitalKeyService.getActiveKeyForBooking(bookingId);
    }

    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getForGuest(@PathVariable Long guestId) {
        return digitalKeyService.getKeysForGuest(guestId);
    }
}
