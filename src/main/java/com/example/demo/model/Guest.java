package com.example.demo.model;

import java.time.Instant;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean verified = false;
    private boolean active = true;
    private String role = "ROLE_USER";
    private Instant createdAt;
    public void setId(Long id) {
        this.id = id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public boolean isVerified() {
        return verified;
    }
    public Boolean getActive() {
        return active;
    }
    public String getRole() {
        return role;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public Guest() {
    }
    public Guest(Long id, String fullName, String email, String phoneNumber, String password, boolean verified,
            boolean active, String role, Instant createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.verified = verified;
        this.active = active;
        this.role = role;
        this.createdAt = createdAt;
    }

    

}
