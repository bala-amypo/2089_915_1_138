package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {
    KeyShareRequestService keyShareRequestService;

    @PostMapping
    public KeyShareRequest create(@RequestBody KeyShareRequest request) {
        return keyShareRequestService.createShareRequest(request);
    }

    @GetMapping("/{id}")
    public KeyShareRequest getById(@PathVariable Long id) {
        return keyShareRequestService.getShareRequestById(id);
    }

    @PutMapping("/{id}/status")
    public KeyShareRequest updateStatus(@PathVariable Long id,
                                        @RequestParam String status) {
        return keyShareRequestService.updateStatus(id, status);
    }

    @GetMapping("/shared-by/{guestId}")
    public List<KeyShareRequest> sharedBy(@PathVariable Long guestId) {
        return keyShareRequestService.getRequestsSharedBy(guestId);
    }

    @GetMapping("/shared-with/{guestId}")
    public List<KeyShareRequest> sharedWith(@PathVariable Long guestId) {
        return keyShareRequestService.getRequestsSharedWith(guestId);
    }

}

