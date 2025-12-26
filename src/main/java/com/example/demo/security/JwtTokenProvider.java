package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    
    @Value("${app.jwt.secret:mySecretKeyForJWTTokenGeneration}")
    private String jwtSecret;
    
    @Value("${app.jwt.validity-ms:86400000}")
    private long validityInMilliseconds;
    
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    public String createToken(String email, String role, Long userId) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);
        claims.put("userId", userId);
        
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(getSigningKey())
                .compact();
    }
    
    public String generateToken(Authentication authentication) {
        GuestPrincipal guestPrincipal = (GuestPrincipal) authentication.getPrincipal();
        String role = guestPrincipal.getAuthorities().iterator().next().getAuthority();
        Long userId = guestPrincipal.getId();
        
        return createToken(guestPrincipal.getUsername(), role, userId);
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    
    public String getEmailFromToken(String token) {
        return getEmail(token);
    }
    
    public String getRole(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().get("role");
    }
    
    public String getRoleFromToken(String token) {
        return getRole(token);
    }
    
    public Long getUserId(String token) {
        return ((Number) Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token).getBody().get("userId")).longValue();
    }
    
    public Long getUserIdFromToken(String token) {
        return getUserId(token);
    }
}