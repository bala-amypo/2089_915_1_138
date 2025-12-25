package com.example.demo.controller;

import com.example.demo.model.RoomBooking;
import com.example.demo.service.RoomBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Room Booking Management", description = "APIs for managing room bookings")
public class RoomBookingController {

    @Autowired
    private RoomBookingService roomBookingService;

    @PostMapping("/")
    @Operation(summary = "Create a new room booking")
    public ResponseEntity<RoomBooking> createBooking(@RequestBody RoomBooking booking) {
        RoomBooking created = roomBookingService.createBooking(booking);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update room booking")
    public ResponseEntity<RoomBooking> updateBooking(@PathVariable Long id, @RequestBody RoomBooking booking) {
        RoomBooking updated = roomBookingService.updateBooking(id, booking);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID")
    public ResponseEntity<RoomBooking> getBooking(@PathVariable Long id) {
        // Need to add this method to service
        return ResponseEntity.ok(new RoomBooking());
    }

    @GetMapping("/guest/{guestId}")
    @Operation(summary = "List bookings for a guest")
    public ResponseEntity<List<RoomBooking>> getBookingsForGuest(@PathVariable Long guestId) {
        List<RoomBooking> bookings = roomBookingService.getBookingsForGuest(guestId);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate a booking")
    public ResponseEntity<Void> deactivateBooking(@PathVariable Long id) {
        // Need to add this method to service
        return ResponseEntity.ok().build();
    }
}