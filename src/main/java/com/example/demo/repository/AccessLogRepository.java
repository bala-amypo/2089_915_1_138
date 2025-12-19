package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long>{
    List<AccessLog> findByGuestId(Long guestId);
    List<AccessLog> findByDigitalKeyId(Long keyId);
}