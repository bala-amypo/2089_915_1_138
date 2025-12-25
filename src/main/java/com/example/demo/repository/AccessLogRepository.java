package com.example.demo.repository;

import com.example.demo.model.AccessLog;
import com.example.demo.model.Guest;
import com.example.demo.model.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
    List<AccessLog> findByGuest(Guest guest);
    List<AccessLog> findByGuestId(Long guestId);
    List<AccessLog> findByDigitalKeyId(Long digitalKeyId);
    List<AccessLog> findByAccessTimeBetween(Instant start, Instant end);
    
    @Query("SELECT al FROM AccessLog al WHERE al.digitalKey = :key")
    List<AccessLog> findByDigitalKey(@Param("key") DigitalKey key);
}