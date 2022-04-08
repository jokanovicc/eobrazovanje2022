package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.service.StudentService;

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //TODO zavrsiti
    @Override
    public StudentDTO findOneByReferenceNumber(String referenceNumber) {
        return studentRepository.findFirstByReferenceNumber(referenceNumber);
    }
}
