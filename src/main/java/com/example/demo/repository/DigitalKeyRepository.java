package com.example.demo.repository;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.RoomBooking;
import com.example.demo.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {
    Optional<DigitalKey> findByBookingAndActiveTrue(RoomBooking booking);
    List<DigitalKey> findByBooking_Guest(Guest guest);
    Optional<DigitalKey> findByBookingIdAndActiveTrue(Long bookingId);
    List<DigitalKey> findByBookingGuestId(Long guestId);
    
    @Query("SELECT dk FROM DigitalKey dk WHERE dk.booking.guest = :guest")
    List<DigitalKey> findDigitalKeysForGuest(@Param("guest") Guest guest);
    
    @Query("SELECT dk FROM DigitalKey dk WHERE dk.booking.guest.email = :email AND dk.active = true")
    List<DigitalKey> findActiveKeysByGuestEmail(@Param("email") String email);
}