package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.StudentMapper;
import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.model.UserRole;
import com.ftn.eobrazovanje.repository.StudentRepository;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserService userService;

    public StudentServiceImpl(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }
    //TODO zavrsiti
    @Override
    public StudentDTO findOneByReferenceNumber(String referenceNumber) {
        StudentDTO studentDTO = StudentMapper.toDto(studentRepository.findFirstByReferenceNumber(referenceNumber));

        return studentDTO;
    }

    @Override
    public void createFromCSV(MultipartFile file) {
        try {
            List<User> users = CSVHelper.csvToTutorials(file.getInputStream());

            for(User user : users) {
                user.setRole(UserRole.STUDENT);
                User savedUser = userService.create(user);

                Student student = new Student();
                student.setUser(savedUser);
                student.setReferenceNumber(generateRandomReferencialNumber());

                studentRepository.save(student);
            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }



    public static String generateRandomReferencialNumber() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        int len = 7;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

}
