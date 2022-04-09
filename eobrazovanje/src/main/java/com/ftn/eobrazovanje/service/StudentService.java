package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    StudentDTO findOneByReferenceNumber(String referenceNumber);

    void createFromCSV(MultipartFile file);

    StudentDTO findById(Long id);

    List<StudentDTO> findExamRegisteredStudents(Long examId);
}
