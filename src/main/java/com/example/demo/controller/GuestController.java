package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest Management", description = "APIs for managing guests")
@SecurityRequirement(name = "bearerAuth")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/")
    @Operation(summary = "Create a new guest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest created = guestService.createGuest(guest);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update guest information")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        Guest updated = guestService.updateGuest(id, guest);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get guest by ID")
    public ResponseEntity<Guest> getGuest(@PathVariable Long id) {
        Guest guest = guestService.getGuestById(id);
        return ResponseEntity.ok(guest);
    }

    @GetMapping("/")
    @Operation(summary = "List all guests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a guest")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateGuest(@PathVariable Long id) {
        guestService.deactivateGuest(id);
        return ResponseEntity.ok().build();
    }
}