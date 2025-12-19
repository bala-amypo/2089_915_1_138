package com.example.demo.security;

import org.springframework.security.core.Authentication;

public class JwtTokenProvider {
    public String generateToken(Authentication authentication) {
        return "dummy-jwt-token";
    }
}
