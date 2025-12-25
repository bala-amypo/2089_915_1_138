package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "digital_keys")
public class DigitalKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private RoomBooking booking;
    
    @Column(unique = true, nullable = false)
    private String keyValue;
    
    private Instant issuedAt;
    private Instant expiresAt;
    
    private Boolean active = true;
    
    public DigitalKey() {}
    
    public DigitalKey(RoomBooking booking, String keyValue) {
        this.booking = booking;
        this.keyValue = keyValue;
        this.issuedAt = Instant.now();
        this.expiresAt = Instant.now().plusSeconds(3600);
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public RoomBooking getBooking() { return booking; }
    public void setBooking(RoomBooking booking) { this.booking = booking; }
    
    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }
    
    public Instant getIssuedAt() { return issuedAt; }
    public void setIssuedAt(Instant issuedAt) { this.issuedAt = issuedAt; }
    
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    
    public Boolean isActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Boolean getActive() { return active; }
}