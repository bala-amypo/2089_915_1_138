package com.example.demo.controller;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/access-logs")
@Tag(name = "Access Log Management", description = "APIs for managing access logs")
public class AccessLogController {

    @Autowired
    private AccessLogService accessLogService;

    @PostMapping("/")
    @Operation(summary = "Create access log")
    public ResponseEntity<AccessLog> createLog(@RequestBody AccessLog log) {
        AccessLog created = accessLogService.createLog(log);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/key/{keyId}")
    @Operation(summary = "Get logs for digital key")
    public ResponseEntity<List<AccessLog>> getLogsForKey(@PathVariable Long keyId) {
        List<AccessLog> logs = accessLogService.getLogsForKey(keyId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/guest/{guestId}")
    @Operation(summary = "Get logs for guest")
    public ResponseEntity<List<AccessLog>> getLogsForGuest(@PathVariable Long guestId) {
        List<AccessLog> logs = accessLogService.getLogsForGuest(guestId);
        return ResponseEntity.ok(logs);
    }
}