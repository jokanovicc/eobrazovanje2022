package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.SVFormDTO;
import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.StudentMapper;
import com.ftn.eobrazovanje.helper.CSVHelper;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.model.UserRole;
import com.ftn.eobrazovanje.repository.StudentRepository;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserService userService;

    private final EmailServiceImpl emailService;

    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, UserService userService, EmailServiceImpl emailService, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }
    //TODO zavrsiti
    @Override
    public StudentDTO findOneByReferenceNumber(String referenceNumber) {
        StudentDTO studentDTO = StudentMapper.toDto(studentRepository.findFirstByReferenceNumber(referenceNumber));

        return studentDTO;
    }

    @Override
    public Student findOneByPasswordToken(String token) {
        return studentRepository.findFirstByPasswordToken(token);
    }

    @Override
    public void setFirstPassword(Student student, String password) {
        User user = userService.findOneById(student.getId());

        user.setPassword(passwordEncoder.encode(password));

        userService.update(user);

        student.setFirstLogin(false);
        student.setPasswordToken(null);
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void createFromCSV(MultipartFile file) {
        try {
            List<User> users = CSVHelper.csvToTutorials(file.getInputStream());

            for(User user : users) {
                user.setRole(UserRole.STUDENT);
                user.setUsername(user.getEmail());
                User savedUser = userService.create(user);

                Student student = new Student();
                student.setUser(savedUser);
                student.setReferenceNumber(generateRandomReferencialNumber());

                String passwordToken = UUID.randomUUID().toString();

                student.setPasswordToken(passwordToken);
                student.setFirstLogin(true);
                student.setCompletedSVForm(false);


                studentRepository.save(student);

                emailService.sendEmail(user.getEmail(), "Set password",
                        "Please follow the link and set your password." + " Link: " +
                        "http://localhost:4200/setPassword?token=" + passwordToken);

            }

        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public void setSVForm(SVFormDTO dto, User user, Student student) {
        user.setName(dto.getName());
        user.setLastname(dto.getLastName());
        user.setAddress(dto.getAddress());
        user.setJmbg(dto.getJmbg());
        if (dto.getGender().equals("muški")) {
            user.setGender("MALE");
        } else{
            user.setGender("FEMALE");
        }

        userService.update(user);


        student.setIndexNumber(dto.getIndex());
        student.setCompletedSVForm(true);
        studentRepository.save(student);


    }

    @Override
    public StudentDTO findById(Long id) {
        return StudentMapper.toDto(studentRepository.findById(id).orElse(null));
    }

    @Override
    public Student findOneByIndexNumber(String index) {
        return studentRepository.findFirstByIndexNumber(index);
    }

    @Override
    public List<StudentDTO> findByPerformanceId(Long id) {
        return StudentMapper.toDTOList(studentRepository.findByPerformanceId(id));
    }

    @Override
    public Student findByUserId(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    @Override
    public List<StudentDTO> findExamRegisteredStudents(Long examId) {
        List<User> users;
        users = userService.findRegisteredToExamUser(examId);
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (User u:users) {
            StudentDTO student = findById(u.getId());
            studentDTOS.add(student);

        }
        return studentDTOS;
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
