package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Guest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.GuestService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    GuestService guestService;
    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    PasswordEncoder passwordEncoder;

    public AuthController(GuestService guestService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.guestService = guestService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Guest> register(@RequestBody RegisterRequest request) {

        Guest guest = new Guest();
        guest.setFullName(request.getFullName());
        guest.setEmail(request.getEmail());
        guest.setPhoneNumber(request.getPhoneNumber());
        guest.setPassword(request.getPassword());
        guest.setRole(request.getRole());

        Guest createdGuest = guestService.createGuest(guest);
        return ResponseEntity.ok(createdGuest);
    }
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    String token = jwtTokenProvider.generateToken(authentication);

    Guest guest = guestService.getGuestByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    Map<String, Object> response = new HashMap<>();
    response.put("token", token);
    response.put("guest", guest);

    return ResponseEntity.ok(response);
}

}
