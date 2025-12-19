package com.example.demo.model;

import java.sql.Timestamp;

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

    private Timestamp shareStart;
    private Timestamp shareEnd;
    private String status;
    private Timestamp createdAt;
    public void setId(Long id) {
        this.id = id;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public void setsharedBy(Guest sharedBy) {
        this.sharedBy = sharedBy;
    }
    public void setSharedWith(Guest sharedWith) {
        this.sharedWith = sharedWith;
    }
    public void setShareStart(Timestamp shareStart) {
        this.shareStart = shareStart;
    }
    public void setShareEnd(Timestamp shareEnd) {
        this.shareEnd = shareEnd;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public Guest getsharedBy() {
        return sharedBy;
    }
    public Guest getSharedWith() {
        return sharedWith;
    }
    public Timestamp getShareStart() {
        return shareStart;
    }
    public Timestamp getShareEnd() {
        return shareEnd;
    }
    public String getStatus() {
        return status;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public KeyShareRequest(Long id, DigitalKey digitalKey, Guest sharedBy, Guest sharedWith, Timestamp shareStart,
            Timestamp shareEnd, String status, Timestamp createdAt) {
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
