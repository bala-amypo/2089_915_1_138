package com.example.demo.repository;

import com.example.demo.model.RoomBooking;
import com.example.demo.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    List<RoomBooking> findByGuest(Guest guest);
    List<RoomBooking> findByGuestAndActiveTrue(Guest guest);
    List<RoomBooking> findByGuestId(Long guestId);
}