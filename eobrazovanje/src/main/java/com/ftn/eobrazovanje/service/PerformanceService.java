package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.*;
import com.ftn.eobrazovanje.model.CourseTeacher;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.TeacherRole;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PerformanceService {

    Performance save(Performance performance);

    List<PerformanceResponseDTO> getAll();

    Performance findById(Long id);

    PerformanceTeachersDTO getByIdWithTeachers(Long id);

    Performance findByCourseId(Long id);

    void addTeacherToPerformance(Teacher teacher, String role, Performance performance);

    void removeTeacherFromPerformance(Performance performance, Long id);

    CoursePerformanceDTO create(CreateCoursePerformanceRequest coursePerformance);

    List<CoursePerformanceDTO> get(Authentication authentication);

    List<TeacherDTO> getTeachers(Long perfId);
}