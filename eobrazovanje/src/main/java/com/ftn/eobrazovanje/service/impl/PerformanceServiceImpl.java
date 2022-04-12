package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.CoursePerformanceDTO;
import com.ftn.eobrazovanje.api.dto.CourseTeacherRequest;
import com.ftn.eobrazovanje.api.dto.CreateCoursePerformanceRequest;
import com.ftn.eobrazovanje.api.dto.TeacherToAttendingDTO;
import com.ftn.eobrazovanje.api.dto.mapper.CoursePerformanceMapper;
import com.ftn.eobrazovanje.exception.BadRequestException;
import com.ftn.eobrazovanje.model.*;
import com.ftn.eobrazovanje.repository.CourseRepository;
import com.ftn.eobrazovanje.repository.PerformanceRepository;
import com.ftn.eobrazovanje.repository.TeacherRepository;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public Performance findById(Long id) {
        return performanceRepository.findById(id).orElse(null);
    }

    @Override
    public Performance findByCourseId(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return performanceRepository.findFirstByCourse(course);
    }

    @Override
    public void addTeacherToPerformance(Teacher teacher, String role, Performance performance) {
        CourseTeacher courseTeacher = new CourseTeacher();
        TeacherRole teacherRole = teacherService.findByName(role);
        if(teacherRole == null) {
            throw new BadRequestException("Invalid teacher role for teacher " + teacher.getUser().getUsername());
        }
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherRole(teacherRole);
        teacherService.saveCourseTeacher(courseTeacher);
        performance.getCourseTeachers().add(courseTeacher);
        save(performance);
    }

    @Override
    public void removeTeacherFromPerformance(Performance performance, CourseTeacher courseTeacher) {
        performance.getCourseTeachers().remove(courseTeacher);
        save(performance);
    }

    @Override
    public CoursePerformanceDTO create(CreateCoursePerformanceRequest performanceRequest) {
        Course course = courseRepository.findById(performanceRequest.getCourseId()).get();

        Performance performance = new Performance(
                performanceRequest.getSchoolYear(),
                new ArrayList<>(),
                course
        );
        if(performanceRequest.getTeachers() != null && !performanceRequest.getTeachers().isEmpty()) {
            mapTeachers(performanceRequest.getTeachers(), performance);
        }

        performanceRepository.save(performance);
        return CoursePerformanceMapper.toDto(performance);
    }

    private void mapTeachers(List<CourseTeacherRequest> teacherRequest, Performance performance) {
        List<Long> teacherIds =  teacherRequest.stream()
                .map(teacher -> teacher.getTeacherId())
                .collect(Collectors.toList());

        List<Teacher> teachers = teacherRepository.findAllById(teacherIds);

        for(CourseTeacherRequest teacherFromRequest : teacherRequest) {
            for(Teacher teacherObj : teachers) {
                if(teacherFromRequest.getTeacherId().equals(teacherObj.getId())) {
                    addTeacherToPerformance(teacherObj, teacherFromRequest.getRole(), performance);
                }
            }
        }
    }
}
