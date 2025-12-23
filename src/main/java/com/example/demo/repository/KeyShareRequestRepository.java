package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface KeyShareRequestRepository extends JpaRepository<KeyShareRequest, Long> {
    List<KeyShareRequest> findBySharedWithId(Long guestId);
    List<KeyShareRequest> findBySharedById(Long guestId);

    @Query("SELECT ksr FROM KeyShareRequest ksr " +
           "JOIN FETCH ksr.digitalKey " +
           "JOIN FETCH ksr.sharedBy " +
           "JOIN FETCH ksr.sharedWith " +
           "WHERE ksr.id = :id")
    Optional<KeyShareRequest> findByIdWithAssociations(@Param("id") Long id);
}   