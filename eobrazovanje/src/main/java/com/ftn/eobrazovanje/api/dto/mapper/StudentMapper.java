package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.ReqPaymentDTO;
import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.model.Payment;
import com.ftn.eobrazovanje.model.Student;

public class StudentMapper {

    public static Student toEntity(StudentDTO dto) {
        return new Student(
                dto.getId(),
                dto.getIndexNumber(),
                UserMapper.toEntity(dto.getUser()),
                dto.getReferenceNumber()

        );
    }

    public static StudentDTO toDto(Student student){
        return new StudentDTO(
                student.getId(),
                student.getIndexNumber(),
                UserMapper.toDto(student.getUser()),
                student.getReferenceNumber()
        );
    }

}
