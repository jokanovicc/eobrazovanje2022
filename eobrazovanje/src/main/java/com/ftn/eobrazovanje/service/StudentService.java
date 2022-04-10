package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.FirstPasswordDTO;
import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.model.Student;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

    StudentDTO findOneByReferenceNumber(String referenceNumber);

    Student findOneByPasswordToken(String token);

    void setFirstPassword(Student student, String password);

    void createFromCSV(MultipartFile file);
}
