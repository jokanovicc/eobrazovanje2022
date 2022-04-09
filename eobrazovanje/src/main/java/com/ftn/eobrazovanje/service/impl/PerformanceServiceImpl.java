package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.TeacherToAttendingDTO;
import com.ftn.eobrazovanje.model.CourseTeacher;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.TeacherRole;
import com.ftn.eobrazovanje.repository.PerformanceRepository;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceServiceImpl implements PerformanceService {

    @Autowired
    PerformanceRepository performanceRepository;

    @Autowired
    TeacherService teacherService;

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public Performance findById(Long id) {
        return performanceRepository.findById(id).orElse(null);
    }

    @Override
    public void addTeacherToPerformance(Teacher teacher, String role, TeacherService teacherService, Performance performance) {
        CourseTeacher courseTeacher = new CourseTeacher();
        TeacherRole teacherRole = teacherService.findByName(role);
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
}
