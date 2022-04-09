package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.TeacherToAttendingDTO;
import com.ftn.eobrazovanje.model.CourseTeacher;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.TeacherRole;

public interface PerformanceService {

    Performance save(Performance performance);

    Performance findById(Long id);

    void addTeacherToPerformance(Teacher teacher, String role, TeacherService teacherService, Performance performance);

    void removeTeacherFromPerformance(Performance performance, CourseTeacher courseTeacher);

}