package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/access-logs")
public class AccessLogController {
    
    AccessLogService accessLogService;

    @PostMapping
    public AccessLog create(@RequestBody AccessLog log) {
        return accessLogService.createLog(log);
    }

    @GetMapping("/key/{keyId}")
    public List<AccessLog> getForKey(@PathVariable Long keyId) {
        return accessLogService.getLogsForKey(keyId);
    }

    @GetMapping("/guest/{guestId}")
    public List<AccessLog> getForGuest(@PathVariable Long guestId) {
        return accessLogService.getLogsForGuest(guestId);
    }
}
