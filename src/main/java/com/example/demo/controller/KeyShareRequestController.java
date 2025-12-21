package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-share")
@Tag(name = "Key Sharing")
public class KeyShareRequestController {
    
    private final KeyShareRequestService keyShareRequestService;
    
    public KeyShareRequestController(KeyShareRequestService keyShareRequestService) {
        this.keyShareRequestService = keyShareRequestService;
    }
    
    @PostMapping
    @Operation(summary = "Create key share request")
    public ResponseEntity<KeyShareRequest> createShareRequest(@RequestBody KeyShareRequest request) {
        KeyShareRequest createdRequest = keyShareRequestService.createShareRequest(request);
        return ResponseEntity.ok(createdRequest);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get share request by ID")
    public ResponseEntity<KeyShareRequest> getShareRequestById(@Parameter(name = "id", description = "Request ID") @PathVariable Long id) {
        KeyShareRequest request = keyShareRequestService.getShareRequestById(id);
        return ResponseEntity.ok(request);
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update share request status")
    public ResponseEntity<KeyShareRequest> updateStatus(@Parameter(name = "id", description = "Request ID") @PathVariable Long id, 
                                                       @Parameter(name = "status", description = "New status") @RequestParam String status) {
        KeyShareRequest updatedRequest = keyShareRequestService.updateStatus(id, status);
        return ResponseEntity.ok(updatedRequest);
    }
    
    @GetMapping("/shared-by/{guestId}")
    @Operation(summary = "Get requests shared by guest")
    public ResponseEntity<List<KeyShareRequest>> getRequestsSharedBy(@Parameter(name = "guestId", description = "Guest ID") @PathVariable Long guestId) {
        List<KeyShareRequest> requests = keyShareRequestService.getRequestsSharedBy(guestId);
        return ResponseEntity.ok(requests);
    }
    
    @GetMapping("/shared-with/{guestId}")
    @Operation(summary = "Get requests shared with guest")
    public ResponseEntity<List<KeyShareRequest>> getRequestsSharedWith(@Parameter(name = "guestId", description = "Guest ID") @PathVariable Long guestId) {
        List<KeyShareRequest> requests = keyShareRequestService.getRequestsSharedWith(guestId);
        return ResponseEntity.ok(requests);
    }
}