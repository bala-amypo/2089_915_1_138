package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.AccessLogService;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    AccessLogRepository accessLogRepository;
    DigitalKeyRepository digitalKeyRepository;
    GuestRepository guestRepository;
    KeyShareRequestRepository keyShareRequestRepository;

    public AccessLogServiceImpl(AccessLogRepository accessLogRepository,
                                DigitalKeyRepository digitalKeyRepository,
                                GuestRepository guestRepository,
                                KeyShareRequestRepository keyShareRequestRepository) {
        this.accessLogRepository = accessLogRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
        this.keyShareRequestRepository = keyShareRequestRepository;
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
