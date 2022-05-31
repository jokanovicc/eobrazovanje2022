package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.AttendingBulkResponseDTO;
import com.ftn.eobrazovanje.api.dto.AttendingDTO;
import com.ftn.eobrazovanje.api.dto.AttendingPaginableDTO;
import com.ftn.eobrazovanje.api.dto.StudentsAttendingPerformanceDTO;
import com.ftn.eobrazovanje.api.dto.mapper.AttendingMapper;
import com.ftn.eobrazovanje.exception.BadRequestException;
import com.ftn.eobrazovanje.exception.NotFoundException;
import com.ftn.eobrazovanje.exception.StudentNonExistentException;
import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.model.*;
import com.ftn.eobrazovanje.repository.AttendingRepository;
import com.ftn.eobrazovanje.service.AttendingService;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class AttendingServiceImpl implements AttendingService {


    private final AttendingRepository attendingRepository;

    private final PerformanceService performanceService;

    private final StudentService studentService;

    private final UserService userService;

    public AttendingServiceImpl(
            AttendingRepository attendingRepository,
            PerformanceService performanceService,
            StudentService studentService,
            UserService userService
    ) {
        this.attendingRepository = attendingRepository;
        this.performanceService = performanceService;
        this.studentService = studentService;
        this.userService = userService;
    }


    @Override
    public Attending findById(Long id) {
        return attendingRepository.findById(id).orElse(null);
    }

    @Override
    public List<String> create(StudentsAttendingPerformanceDTO dto, Long performanceId) throws BadRequestException{
        Performance performance = performanceService.findById(performanceId);

        List<String> wrongIndexes = new ArrayList<>();
        for(String index : dto.getIndexNumbers()){
            Student student = studentService.findOneByIndexNumber(index);
            Attending existAttenging = attendingRepository.findFirstByStudentAndPerformance(student, performance);

            if(existAttenging != null){
                throw new BadRequestException("Already exist");
            }

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

    @Override
    public AttendingDTO findLatestOfCourseForStudent(Authentication authentication, Long performanceId) {
        User user = userService.getUser(authentication);
        List<Attending> attending = attendingRepository.findByStudentAndPerformance(user.getId(), performanceId);
        if(attending.size() == 1) {
            return AttendingMapper.toDTO(attending.get(0));
        }
        Attending latest = attending.stream().max(Comparator.comparing(v -> v.getPerformance().getSchoolYear())).get();
        return AttendingMapper.toDTO(latest);
    }

    @Override
    public AttendingPaginableDTO getStudentsOfCourse(Long performanceId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Attending> attendings = attendingRepository.findByPerformance(performanceId, pageable);
        List<AttendingDTO> attendingDTOS = new ArrayList<>();
        for(Attending a: attendings){
            attendingDTOS.add(AttendingMapper.toDTO(a));
        }

        return new AttendingPaginableDTO(attendingDTOS, attendings.getTotalPages());
    }

    @Override
    public AttendingBulkResponseDTO addStudentsFromFile(MultipartFile file, Long performanceId) {
        try {
            List<String> mails = CSVHelper.getListOfStudentsMail(file.getInputStream());
            Performance performance = performanceService.findById(performanceId);
            AttendingBulkResponseDTO response = new AttendingBulkResponseDTO();
            List<String> added = new ArrayList<>();
            List<String> alreadyAdded = new ArrayList<>();
            List<String> createdAndAdded = new ArrayList<>();
            for(String mail: mails){
                User user = userService.findOneByEmail(mail);
                if(user == null){
                    User newUser = new User();
                    newUser.setEmail(mail);
                    Student createdStudent = studentService.createStudent(newUser);
                    Attending attending = new Attending(null, performance, createdStudent);
                    attendingRepository.save(attending);
                    createdAndAdded.add(mail);
                }else{
                    Student student = studentService.findByUserId(user.getId());
                    Attending existAttenging = attendingRepository.findFirstByStudentAndPerformance(student, performance);
                    if(existAttenging !=null){
                        alreadyAdded.add(mail);
                        continue;
                    }
                    Attending attending = new Attending(null, performance, student);
                    attendingRepository.save(attending);
                    added.add(mail);
                }
            }
            response.setAdded(added);
            response.setAlreadyAdded(alreadyAdded);
            response.setCreatedAndAdded(createdAndAdded);
            return response;


        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }


}
