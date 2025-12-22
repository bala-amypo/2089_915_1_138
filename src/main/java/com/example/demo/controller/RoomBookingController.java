package com.example.demo.controller;

import com.example.demo.model.RoomBooking;
import com.example.demo.service.RoomBookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Room Bookings", description = "Operations related to room bookings")
@RestController
@RequestMapping("/api/bookings")
public class RoomBookingController {
    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @PostMapping
    public ResponseEntity<RoomBooking> createBooking(@RequestBody RoomBooking booking) {
        return ResponseEntity.ok(roomBookingService.createBooking(booking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomBooking> getBookingById(@PathVariable Long id) {
        try {
            RoomBooking booking = roomBookingService.getBookingById(id);
            return ResponseEntity.ok(booking);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching booking: " + e.getMessage());
        }
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<RoomBooking>> getBookingsForGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(roomBookingService.getBookingsForGuest(guestId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomBooking> updateBooking(@PathVariable Long id, @RequestBody RoomBooking booking) {
        return ResponseEntity.ok(roomBookingService.updateBooking(id, booking));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBooking(@PathVariable Long id) {
        roomBookingService.deactivateBooking(id);
        return ResponseEntity.ok().build();
    }
}