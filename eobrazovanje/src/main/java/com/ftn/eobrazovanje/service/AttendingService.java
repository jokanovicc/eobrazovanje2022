package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.AttendingBulkResponseDTO;
import com.ftn.eobrazovanje.api.dto.AttendingDTO;
import com.ftn.eobrazovanje.api.dto.AttendingPaginableDTO;
import com.ftn.eobrazovanje.api.dto.StudentsAttendingPerformanceDTO;
import com.ftn.eobrazovanje.model.Attending;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttendingService {


    Attending findById(Long id);

    List<String> create(StudentsAttendingPerformanceDTO dto, Long performanceId);

    boolean delete(Long studentId, Long performanceId);

    AttendingDTO findLatestOfCourseForStudent(Authentication authentication, Long performanceId);

    AttendingPaginableDTO getStudentsOfCourse(Long performanceId, Integer page);

    AttendingBulkResponseDTO addStudentsFromFile(MultipartFile file, Long performanceId);

}
