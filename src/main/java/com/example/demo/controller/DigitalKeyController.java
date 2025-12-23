package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Digital Keys", description = "Operations related to digital keys")
@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {
    private final DigitalKeyService digitalKeyService;

    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    @PostMapping("/generate/{bookingId}")
    public ResponseEntity<DigitalKey> generateKey(@PathVariable Long bookingId) {
        return ResponseEntity.ok(digitalKeyService.generateKey(bookingId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalKey> getKeyById(@PathVariable Long id) {
        return ResponseEntity.ok(digitalKeyService.getKeyById(id));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<DigitalKey> getActiveKeyForBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(digitalKeyService.getActiveKeyForBooking(bookingId));
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<DigitalKey>> getKeysForGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(digitalKeyService.getKeysForGuest(guestId));
    }
}