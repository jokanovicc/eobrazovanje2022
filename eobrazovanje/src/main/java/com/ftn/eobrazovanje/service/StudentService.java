package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.FirstPasswordDTO;
import com.ftn.eobrazovanje.api.dto.SVFormDTO;
import com.ftn.eobrazovanje.api.dto.StudentDTO;

import com.ftn.eobrazovanje.api.dto.StudentResponseDTO;
import com.ftn.eobrazovanje.model.Student;

import com.ftn.eobrazovanje.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    StudentDTO findOneByReferenceNumber(String referenceNumber);

    Student findOneByPasswordToken(String token);

    StudentDTO findById(Long id);

    Student findOneByIndexNumber(String index);

    List<StudentDTO> findByPerformanceId(Long id);

    Student findByUserId(Long id);

    List<StudentDTO> findExamRegisteredStudents(Long examId);

    void setFirstPassword(Student student, String password);

    void createFromCSV(MultipartFile file);

    void setSVForm(SVFormDTO dto, User user, Student student);

    StudentResponseDTO getAll(Integer pageNo);

    Student createStudent(User user);




}
