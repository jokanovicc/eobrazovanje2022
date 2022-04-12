package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.StudentsAttendingPerformanceDTO;
import com.ftn.eobrazovanje.model.Attending;

import java.util.List;

public interface AttendingService {


    Attending findById(Long id);

    List<String> create(StudentsAttendingPerformanceDTO dto, Long performanceId);

    boolean delete(Long studentId, Long performanceId);
}
