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
@Table(name = "access_logs")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digital_key_id")
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name ="guest_id")
    private Guest guest;
    private Instant accessTime;
    private String result;
    private String reason;
    public void setId(Long id) {
        this.id = id;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setAccessTime(Instant accessTime) {
        this.accessTime = accessTime;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Long getId() {
        return id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public Guest getGuest() {
        return guest;
    }
    public Instant getAccessTime() {
        return accessTime;
    }
    public String getResult() {
        return result;
    }
    public String getReason() {
        return reason;
    }
    public AccessLog(Long id, DigitalKey digitalKey, Guest guest, Instant accessTime, String result, String reason) {
        this.id = id;
        this.digitalKey = digitalKey;
        this.guest = guest;
        this.accessTime = accessTime;
        this.result = result;
        this.reason = reason;
    }
    public AccessLog() {
    }

    
    
}
