package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.repository.TeacherRepository;
import com.ftn.eobrazovanje.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;


    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers(Integer pageNo){
        Pageable paging = PageRequest.of(pageNo,5);
        Page<Teacher> pagedResult = teacherRepository.findAll(paging);
        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else{
            return new ArrayList<Teacher>();
        }

    }


}
