package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.repository.TeacherRepository;
import com.ftn.eobrazovanje.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
