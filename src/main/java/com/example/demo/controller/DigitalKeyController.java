package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital-keys")
@Tag(name = "Digital Keys")
public class DigitalKeyController {
    
    private final DigitalKeyService digitalKeyService;
    
    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }
    
    @PostMapping("/generate/{bookingId}")
    @Operation(summary = "Generate digital key for booking")
    public ResponseEntity<DigitalKey> generateKey(@Parameter(name = "bookingId", description = "Booking ID") @PathVariable Long bookingId) {
        DigitalKey digitalKey = digitalKeyService.generateKey(bookingId);
        return ResponseEntity.ok(digitalKey);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get digital key by ID")
    public ResponseEntity<DigitalKey> getKeyById(@Parameter(name = "id", description = "Key ID") @PathVariable Long id) {
        DigitalKey digitalKey = digitalKeyService.getKeyById(id);
        return ResponseEntity.ok(digitalKey);
    }
    
    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "Get active key for booking")
    public ResponseEntity<DigitalKey> getActiveKeyForBooking(@Parameter(name = "bookingId", description = "Booking ID") @PathVariable Long bookingId) {
        DigitalKey digitalKey = digitalKeyService.getActiveKeyForBooking(bookingId);
        return ResponseEntity.ok(digitalKey);
    }
    
    @GetMapping("/guest/{guestId}")
    @Operation(summary = "Get keys for guest")
    public ResponseEntity<List<DigitalKey>> getKeysForGuest(@Parameter(name = "guestId", description = "Guest ID") @PathVariable Long guestId) {
        List<DigitalKey> digitalKeys = digitalKeyService.getKeysForGuest(guestId);
        return ResponseEntity.ok(digitalKeys);
    }
}