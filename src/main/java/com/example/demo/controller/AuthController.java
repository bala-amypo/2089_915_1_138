package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.TokenResponse;
import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.GuestPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "APIs for user authentication")
public class AuthController {

    @Autowired
    private GuestService guestService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest request) {
        Guest guest = new Guest();
        guest.setFullName(request.getFullName());
        guest.setEmail(request.getEmail());
        guest.setPhoneNumber(request.getPhoneNumber());
        guest.setPassword(request.getPassword());
        guest.setRole(request.getRole());
        
        Guest created = guestService.createGuest(guest);
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
        String token = jwtTokenProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new TokenResponse(token, created.getId(), created.getEmail(), created.getRole()));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user and get JWT token")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        String token = jwtTokenProvider.generateToken(authentication);
        
        GuestPrincipal principal = (GuestPrincipal) authentication.getPrincipal();
        Guest guest = principal.getGuest();
        
        return ResponseEntity.ok(new TokenResponse(token, guest.getId(), guest.getEmail(), guest.getRole()));
    }
}