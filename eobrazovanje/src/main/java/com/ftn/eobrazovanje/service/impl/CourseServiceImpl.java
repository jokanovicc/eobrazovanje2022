package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.mapper.CourseMapper;
import com.ftn.eobrazovanje.exception.UserNonExistentException;
import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.model.UserRole;
import com.ftn.eobrazovanje.repository.CourseRepository;
import com.ftn.eobrazovanje.repository.ExamRepository;
import com.ftn.eobrazovanje.service.CourseService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<CourseDTO> getCoursesForUser(Authentication authentication) {
        Optional<User> user = userService.getUser(authentication);
        if(user.isEmpty()) {
            throw new UserNonExistentException("User doesn't exist");
        }

        UserRole role = user.get().getRole();
        Long userId = user.get().getId();

        if(role == UserRole.STUDENT) {
            return this.getCoursesForStudent(userId);
        }

        if(role == UserRole.TEACHER) {
            return this.getCoursesForTeacher(userId);
        }

        return this.getCoursesForAdmin();
    }

    private List<CourseDTO> getCoursesForStudent(Long studentId) {
        return CourseMapper.toDtoList(courseRepository.getByStudentId(studentId));
    }

    private List<CourseDTO> getCoursesForTeacher(Long teacherId) {
        return CourseMapper.toDtoList(courseRepository.getByTeacherId(teacherId));
    }

    private List<CourseDTO> getCoursesForAdmin() {
        return CourseMapper.toDtoList(courseRepository.findAll());
    }
}
