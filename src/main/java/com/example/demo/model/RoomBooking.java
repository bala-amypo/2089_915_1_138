package com.example.demo.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_bookings")
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean active = true;
    @ManyToMany
    @JoinTable(
        name = "room_booking_roommates",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "guest_id")
    )

    private Set<Guest> roommates = new HashSet<>();

    
    public void setId(Long id) {
        this.id = id;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setRoommates(Set<Guest> roommates) {
        this.roommates = roommates;
    }
    public Long getId() {
        return id;
    }
    public Guest getGuest() {
        return guest;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public boolean isActive() {
        return active;
    }
    public Set<Guest> getRoommates() {
        return roommates;
    }
    public RoomBooking() {
    }
    public RoomBooking(Long id, Guest guest, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate,
            boolean active, Set<Guest> roommates) {
        this.id = id;
        this.guest = guest;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.active = active;
        this.roommates = roommates;
    }
    

    
}
