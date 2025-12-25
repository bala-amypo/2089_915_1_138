package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    private Boolean active = true;
    private Boolean verified = false;
    private String role = "USER";
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @ManyToMany(mappedBy = "roommates")
    private Set<RoomBooking> roommateBookings = new HashSet<>();
    
    public Guest() {}
    
    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
    
    public Guest(String email, String firstName, String lastName, String password, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public Boolean isActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Boolean getActive() { return active; }
    
    public Boolean isVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }
    public Boolean getVerified() { return verified; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getFullName() { 
        if (lastName == null || lastName.trim().isEmpty()) {
            return firstName;
        }
        return firstName + " " + lastName; 
    }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return;
        }
        String[] parts = fullName.trim().split(" ", 2);
        this.firstName = parts[0];
        this.lastName = parts.length > 1 ? parts[1] : "";
    }
    
    public Set<RoomBooking> getRoommateBookings() { return roommateBookings; }
    public void setRoommateBookings(Set<RoomBooking> roommateBookings) { this.roommateBookings = roommateBookings; }
    
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}