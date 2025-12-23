package com.example.demo.controller;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Access Logs", description = "Operations related to access logs")
@RestController
@RequestMapping("/api/access-logs")
public class AccessLogController {
    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @PostMapping
    public ResponseEntity<AccessLog> createLog(@RequestBody AccessLog log) {
        return ResponseEntity.ok(accessLogService.createLog(log));
    }

    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<AccessLog>> getLogsForKey(@PathVariable Long keyId) {
        return ResponseEntity.ok(accessLogService.getLogsForKey(keyId));
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<AccessLog>> getLogsForGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(accessLogService.getLogsForGuest(guestId));
    }
}