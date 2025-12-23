package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication", description = "Authentication operations")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final GuestService guestService;

    public AuthController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            Guest guest = new Guest();
            guest.setEmail(registerRequest.getEmail());
            guest.setPassword(registerRequest.getPassword());
            guest.setFullName(registerRequest.getFullName());
            guest.setPhoneNumber(registerRequest.getPhoneNumber());
            guest.setRole(registerRequest.getRole() != null ? registerRequest.getRole() : "ROLE_USER");
            guest.setVerified(false);
            guest.setActive(true);

            Guest createdGuest = guestService.createGuest(guest);
            return ResponseEntity.ok(new ApiResponse(true, "Registration successful", createdGuest));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "Registration failed"));
        }
    }
}