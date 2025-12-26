package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital-keys")
@Tag(name = "Digital Key Management", description = "APIs for managing digital keys")
@SecurityRequirement(name = "bearerAuth")
public class DigitalKeyController {

    @Autowired
    private DigitalKeyService digitalKeyService;

    @PostMapping("/generate/{bookingId}")
    @Operation(summary = "Generate digital key for booking")
    public ResponseEntity<DigitalKey> generateKey(@PathVariable Long bookingId) {
        DigitalKey key = digitalKeyService.generateKey(bookingId);
        return ResponseEntity.ok(key);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get digital key by ID")
    public ResponseEntity<DigitalKey> getKey(@PathVariable Long id) {
        DigitalKey key = digitalKeyService.getKeyById(id);
        return ResponseEntity.ok(key);
    }

    @GetMapping("/booking/{bookingId}")
    @Operation(summary = "Get active key for booking")
    public ResponseEntity<DigitalKey> getActiveKeyForBooking(@PathVariable Long bookingId) {
        DigitalKey key = digitalKeyService.getActiveKeyForBooking(bookingId);
        return ResponseEntity.ok(key);
    }

    @GetMapping("/guest/{guestId}")
    @Operation(summary = "List keys for guest")
    public ResponseEntity<List<DigitalKey>> getKeysForGuest(@PathVariable Long guestId) {
        List<DigitalKey> keys = digitalKeyService.getKeysForGuest(guestId);
        return ResponseEntity.ok(keys);
    }
}