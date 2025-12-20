package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "digital_keys")
public class DigitalKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private RoomBooking booking;
    private String keyValue;
    private Instant issuedAt;
    private Instant expiresAt;
    private boolean active;

    
    public void setId(Long id) {
        this.id = id;
    }
    public void setBooking(RoomBooking booking) {
        this.booking = booking;
    }
    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public RoomBooking getBooking() {
        return booking;
    }
    public String getKeyValue() {
        return keyValue;
    }
    public Instant getIssuedAt() {
        return issuedAt;
    }
    public Instant getExpiresAt() {
        return expiresAt;
    }
    public boolean isActive() {
        return active;
    }
    public DigitalKey(Long id, RoomBooking booking, String keyValue, Instant issuedAt, Instant expiresAt,
            boolean active) {
        this.id = id;
        this.booking = booking;
        this.keyValue = keyValue;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.active = active;
    }
    public DigitalKey() {
    }
}
