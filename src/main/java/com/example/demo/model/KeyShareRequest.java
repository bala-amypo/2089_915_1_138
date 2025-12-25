package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "key_share_requests")
public class KeyShareRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    private DigitalKey digitalKey;
    
    @ManyToOne
    @JoinColumn(name = "shared_by_id", nullable = false)
    private Guest sharedBy;
    
    @ManyToOne
    @JoinColumn(name = "shared_with_id", nullable = false)
    private Guest sharedWith;
    
    @Column(nullable = false)
    private Timestamp shareStart;
    
    @Column(nullable = false)
    private Timestamp shareEnd;
    
    @Column(nullable = false)
    private String status = "PENDING";
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    public KeyShareRequest() {}
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
    
    public KeyShareRequest(DigitalKey digitalKey, Guest sharedBy, Guest sharedWith, Timestamp shareStart, Timestamp shareEnd) {
        this.digitalKey = digitalKey;
        this.sharedBy = sharedBy;
        this.sharedWith = sharedWith;
        this.shareStart = shareStart;
        this.shareEnd = shareEnd;
    }
    
    public KeyShareRequest(DigitalKey digitalKey, Guest sharedBy, Guest sharedWith, Instant shareStart, Instant shareEnd) {
        this.digitalKey = digitalKey;
        this.sharedBy = sharedBy;
        this.sharedWith = sharedWith;
        this.shareStart = Timestamp.from(shareStart);
        this.shareEnd = Timestamp.from(shareEnd);
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }
    
    public Guest getSharedBy() { return sharedBy; }
    public void setSharedBy(Guest sharedBy) { this.sharedBy = sharedBy; }
    
    public Guest getSharedWith() { return sharedWith; }
    public void setSharedWith(Guest sharedWith) { this.sharedWith = sharedWith; }
    
    public Timestamp getShareStart() { return shareStart; }
    public void setShareStart(Timestamp shareStart) { this.shareStart = shareStart; }
    
    @JsonIgnore
    public void setShareStart(Instant shareStart) { this.shareStart = Timestamp.from(shareStart); }
    
    public Timestamp getShareEnd() { return shareEnd; }
    public void setShareEnd(Timestamp shareEnd) { this.shareEnd = shareEnd; }
    
    @JsonIgnore
    public void setShareEnd(Instant shareEnd) { this.shareEnd = Timestamp.from(shareEnd); }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}