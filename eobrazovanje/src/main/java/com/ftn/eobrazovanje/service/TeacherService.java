package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.TeacherResponseDTO;
import com.ftn.eobrazovanje.model.CourseTeacher;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.model.TeacherRole;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);

    TeacherResponseDTO getAllTeachers(Integer pageNo);

    Teacher findById(Long id);

    TeacherRole findByName(String name);

    CourseTeacher saveCourseTeacher(CourseTeacher courseTeacher);

    CourseTeacher findByCourseTeacherByTeacher(Long id);
}
