package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Key Sharing", description = "Operations related to key sharing requests")
@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {
    private final KeyShareRequestService keyShareRequestService;

    public KeyShareRequestController(KeyShareRequestService keyShareRequestService) {
        this.keyShareRequestService = keyShareRequestService;
    }

    @PostMapping
    public ResponseEntity<KeyShareRequest> createShareRequest(@RequestBody KeyShareRequest request) {
        return ResponseEntity.ok(keyShareRequestService.createShareRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyShareRequest> getShareRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(keyShareRequestService.getShareRequestById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<KeyShareRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(keyShareRequestService.updateStatus(id, status));
    }

    @GetMapping("/shared-by/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getRequestsSharedBy(@PathVariable Long guestId) {
        return ResponseEntity.ok(keyShareRequestService.getRequestsSharedBy(guestId));
    }

    @GetMapping("/shared-with/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getRequestsSharedWith(@PathVariable Long guestId) {
        return ResponseEntity.ok(keyShareRequestService.getRequestsSharedWith(guestId));
    }
}