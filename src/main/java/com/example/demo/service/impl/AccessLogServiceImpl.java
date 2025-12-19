package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.service.AccessLogService;

public class AccessLogServiceImpl implements AccessLogService {
    
    AccessLogRepository accessLogRepository;

    public AccessLogServiceImpl(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log){
        return null;
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId){
        return null;
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId){
        return null;
    }
}
