package com.example.demo.security;

public class JwtTokenProvider {

    public String generateToken(org.springframework.security.core.Authentication authentication) {
        return "dummy-token";
    }

    public boolean validateToken(String token) {
        return true;
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }

    public String getEmailFromToken(String token) {
        return "test@example.com";
    }

    public String getRoleFromToken(String token) {
        return "ROLE_USER";
    }
}
