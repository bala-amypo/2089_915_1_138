package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;
import java.time.LocalDateTime;

public interface KeyShareService {
    KeyShareRequest createShareRequest(Long digitalKeyId, Long sharedById, Long sharedWithId, LocalDateTime shareStart, LocalDateTime shareEnd);
}