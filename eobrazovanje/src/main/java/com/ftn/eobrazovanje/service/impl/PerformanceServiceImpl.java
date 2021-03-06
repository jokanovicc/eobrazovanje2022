package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.*;
import com.ftn.eobrazovanje.api.dto.mapper.CoursePerformanceMapper;
import com.ftn.eobrazovanje.api.dto.mapper.CourseTeacherMapper;
import com.ftn.eobrazovanje.api.dto.mapper.TeacherMapper;
import com.ftn.eobrazovanje.exception.BadRequestException;
import com.ftn.eobrazovanje.model.*;
import com.ftn.eobrazovanje.repository.CourseRepository;
import com.ftn.eobrazovanje.repository.CourseTeacherRepository;
import com.ftn.eobrazovanje.repository.PerformanceRepository;
import com.ftn.eobrazovanje.repository.TeacherRepository;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.TeacherService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    private UserService userService;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseTeacherRepository courseTeacherRepository;

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public List<PerformanceResponseDTO> getAll() {
        ArrayList<PerformanceResponseDTO> list = new ArrayList<>();
        for (Performance p:performanceRepository.findAll()) {
            list.add(new PerformanceResponseDTO(p));

        }
        for(PerformanceResponseDTO p: list){
            p.setStudyProgram(performanceRepository.getStudyProgramName(p.getId()));

        }
        return list;
    }

    @Override
    public Performance findById(Long id) {
        return performanceRepository.findById(id).orElse(null);
    }

    @Override
    public PerformanceTeachersDTO getByIdWithTeachers(Long id) {
        Performance performance = performanceRepository.findById(id).orElse(null);

        PerformanceTeachersDTO ptd = new PerformanceTeachersDTO();
        ptd.setCourseName(performance.getCourse().getName());
        ptd.setSchoolYear(performance.getSchoolYear());
        ptd.setCourseSylabus(performance.getCourse().getSylabus());
        ptd.setId(performance.getId());
        List<CourseTeacherDTO> ctd = new ArrayList<>();
        List<Long> teachers = teacherRepository.getTeachers(id);
        for(Long t: teachers){
            CourseTeacher ct = courseTeacherRepository.findById(t).orElse(null);
                    ctd.add(CourseTeacherMapper.toDto(ct));
        }

        ptd.setCourseTeacher(ctd);
        return ptd;
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
            System.out.println("PRC");
        }
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherRole(teacherRole);
        teacherService.saveCourseTeacher(courseTeacher);
        performance.getCourseTeachers().add(courseTeacher);
        save(performance);
    }

    @Override
    public void removeTeacherFromPerformance(Performance performance, Long id) {
        CourseTeacher ctr = courseTeacherRepository.findById(id).orElse(null);
        performance.getCourseTeachers().remove(ctr);
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

    @Override
    public List<CoursePerformanceDTO> get(Authentication authentication) {
        User current = userService.getUser(authentication);
        if(current.getRole() == UserRole.TEACHER) {
            return CoursePerformanceMapper.toDtoList(
                    performanceRepository.findAllByTeacher(current.getId())
            );
        }
        return CoursePerformanceMapper.toDtoList(performanceRepository.findAll());
    }

    @Override
    public List<TeacherDTO> getTeachers(Long perfId) {
        List<Teacher> teachers = teacherRepository.getNotPerformed(perfId);
        return TeacherMapper.toDTOList(teachers);


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
