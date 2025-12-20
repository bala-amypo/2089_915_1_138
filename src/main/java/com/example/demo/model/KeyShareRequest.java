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
@Table(name = "key_share_requests")
public class KeyShareRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digital_key_id")
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "shared_by_id")
    private Guest sharedBy;

    @ManyToOne
    @JoinColumn(name = "shared_with_id")
    private Guest sharedWith;

    private Instant shareStart;
    private Instant shareEnd;
    private String status;
    private Instant createdAt;
    public void setId(Long id) {
        this.id = id;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public void setSharedBy(Guest sharedBy) {
        this.sharedBy = sharedBy;
    }
    public void setSharedWith(Guest sharedWith) {
        this.sharedWith = sharedWith;
    }
    public void setShareStart(Instant shareStart) {
        this.shareStart = shareStart;
    }
    public void setShareEnd(Instant shareEnd) {
        this.shareEnd = shareEnd;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public Guest getSharedBy() {
        return sharedBy;
    }
    public Guest getSharedWith() {
        return sharedWith;
    }
    public Instant getShareStart() {
        return shareStart;
    }
    public Instant getShareEnd() {
        return shareEnd;
    }
    public String getStatus() {
        return status;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public KeyShareRequest(Long id, DigitalKey digitalKey, Guest sharedBy, Guest sharedWith, Instant shareStart,
            Instant shareEnd, String status, Instant createdAt) {
        this.id = id;
        this.digitalKey = digitalKey;
        this.sharedBy = sharedBy;
        this.sharedWith = sharedWith;
        this.shareStart = shareStart;
        this.shareEnd = shareEnd;
        this.status = status;
        this.createdAt = createdAt;
    }
    public KeyShareRequest() {
    }

    
}
