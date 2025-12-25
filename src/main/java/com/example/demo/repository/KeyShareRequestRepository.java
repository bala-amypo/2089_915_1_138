package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeyShareRequestRepository extends JpaRepository<KeyShareRequest, Long> {
    List<KeyShareRequest> findBySharedBy(Guest sharedBy);
    List<KeyShareRequest> findBySharedWith(Guest sharedWith);
    List<KeyShareRequest> findBySharedById(Long sharedById);
    List<KeyShareRequest> findBySharedWithId(Long sharedWithId);
}