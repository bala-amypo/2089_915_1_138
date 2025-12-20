package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Guest;

public interface GuestRepository extends JpaRepository<Guest,Long> {
    Guest findByEmail(String email);

    boolean existsByEmail(String email);
}
