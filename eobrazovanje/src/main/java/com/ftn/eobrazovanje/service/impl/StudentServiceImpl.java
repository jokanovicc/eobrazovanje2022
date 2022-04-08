package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.StudentMapper;
import com.ftn.eobrazovanje.repository.StudentRepository;
import com.ftn.eobrazovanje.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //TODO zavrsiti
    @Override
    public StudentDTO findOneByReferenceNumber(String referenceNumber) {
        StudentDTO studentDTO = StudentMapper.toDto(studentRepository.findFirstByReferenceNumber(referenceNumber));

        return studentDTO;
    }
}
