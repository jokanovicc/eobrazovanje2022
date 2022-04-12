package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.StudentsAttendingPerformanceDTO;
import com.ftn.eobrazovanje.exception.StudentNonExistentException;
import com.ftn.eobrazovanje.model.Attending;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.repository.AttendingRepository;
import com.ftn.eobrazovanje.service.AttendingService;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendingServiceImpl implements AttendingService {


    private final AttendingRepository attendingRepository;

    private final PerformanceService performanceService;

    private final StudentService studentService;

    public AttendingServiceImpl(AttendingRepository attendingRepository, PerformanceService performanceService, StudentService studentService) {
        this.attendingRepository = attendingRepository;
        this.performanceService = performanceService;
        this.studentService = studentService;
    }


    @Override
    public Attending findById(Long id) {
        return attendingRepository.findById(id).orElse(null);
    }

    @Override
    public List<String> create(StudentsAttendingPerformanceDTO dto, Long performanceId){
        Performance performance = performanceService.findById(performanceId);
        List<String> wrongIndexes = new ArrayList<>();
        for(String index : dto.getIndexNumbers()){
            Student student = studentService.findOneByIndexNumber(index);

            if(student != null) {
                Attending attending = new Attending(null, performance, student);
                attendingRepository.save(attending);

            }
            if(student == null){
                wrongIndexes.add(index);
            }

        }

        return wrongIndexes;
    }

    @Override
    public boolean delete(Long studentId, Long performanceId) {
        Student student = studentService.findByUserId(studentId);
        Performance performance = performanceService.findById(performanceId);
        Attending attending = attendingRepository.findFirstByStudentAndPerformance(student, performance);
        if(attending != null){
            attendingRepository.delete(attending);
            return true;
        }
        return false;
    }

}
