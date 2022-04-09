package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);

    List<Teacher> getAllTeachers(Integer pageNo);
}
