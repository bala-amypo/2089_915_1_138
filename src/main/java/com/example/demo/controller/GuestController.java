package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest Management")
public class GuestController {
    
    private final GuestService guestService;
    
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new guest")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest createdGuest = guestService.createGuest(guest);
        return ResponseEntity.ok(createdGuest);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get guest by ID")
    public ResponseEntity<Guest> getGuestById(@Parameter(name = "id", description = "Guest ID") @PathVariable Long id) {
        Guest guest = guestService.getGuestById(id);
        return ResponseEntity.ok(guest);
    }
    
    @GetMapping
    @Operation(summary = "Get all guests")
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update guest")
    public ResponseEntity<Guest> updateGuest(@Parameter(name = "id", description = "Guest ID") @PathVariable Long id, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(id, guest);
        return ResponseEntity.ok(updatedGuest);
    }
    
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate guest")
    public ResponseEntity<Void> deactivateGuest(@Parameter(name = "id", description = "Guest ID") @PathVariable Long id) {
        guestService.deactivateGuest(id);
        return ResponseEntity.ok().build();
    }
}