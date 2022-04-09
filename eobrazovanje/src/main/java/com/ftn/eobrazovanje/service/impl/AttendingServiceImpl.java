package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.model.Attending;
import com.ftn.eobrazovanje.repository.AttendingRepository;
import com.ftn.eobrazovanje.service.AttendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendingServiceImpl implements AttendingService {

    @Autowired
    private AttendingRepository attendingRepository;



    @Override
    public Attending findById(Long id) {
        return attendingRepository.findById(id).orElse(null);
    }
}
