package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    GuestService guestService;

     @PostMapping
    public Guest create(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    @GetMapping("/{id}")
    public Guest getById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @GetMapping
    public List<Guest> getAll() {
        return guestService.getAllGuests();
    }

    @PutMapping("/{id}")
    public Guest update(@PathVariable Long id, @RequestBody Guest guest) {
        return guestService.updateGuest(id, guest);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        guestService.deactivateGuest(id);
    }
}
