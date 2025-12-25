package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-share")
@Tag(name = "Key Share Management", description = "APIs for managing key sharing requests")
public class KeyShareRequestController {

    @Autowired
    private KeyShareRequestService keyShareRequestService;

    @PostMapping("/")
    @Operation(summary = "Create key share request")
    public ResponseEntity<KeyShareRequest> createShareRequest(@RequestBody KeyShareRequest request) {
        KeyShareRequest created = keyShareRequestService.createShareRequest(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update share request status")
    public ResponseEntity<KeyShareRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        // Need to add this method to service
        return ResponseEntity.ok(new KeyShareRequest());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get share request by ID")
    public ResponseEntity<KeyShareRequest> getShareRequest(@PathVariable Long id) {
        // Need to add this method to service
        return ResponseEntity.ok(new KeyShareRequest());
    }

    @GetMapping("/shared-by/{guestId}")
    @Operation(summary = "Get requests shared by guest")
    public ResponseEntity<List<KeyShareRequest>> getRequestsSharedBy(@PathVariable Long guestId) {
        List<KeyShareRequest> requests = keyShareRequestService.getRequestsSharedBy(guestId);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/shared-with/{guestId}")
    @Operation(summary = "Get requests shared with guest")
    public ResponseEntity<List<KeyShareRequest>> getRequestsSharedWith(@PathVariable Long guestId) {
        List<KeyShareRequest> requests = keyShareRequestService.getRequestsSharedWith(guestId);
        return ResponseEntity.ok(requests);
    }
}