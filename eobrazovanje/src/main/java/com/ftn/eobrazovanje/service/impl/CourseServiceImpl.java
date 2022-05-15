package com.ftn.eobrazovanje.service.impl;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.CourseResponseDTO;
import com.ftn.eobrazovanje.api.dto.CreateCourseDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ExamRepository examRepository;



    @Override
    public List<CourseDTO> getCoursesForUser(Authentication authentication) {
        User user = userService.getUser(authentication);

        UserRole role = user.getRole();
        Long userId = user.getId();

        if(role == UserRole.STUDENT) {
            return this.getCoursesForStudent(userId);
        }

        if(role == UserRole.TEACHER) {
            return this.getCoursesForTeacher(userId);
        }

        return this.getCoursesForAdmin();
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);


    }

    @Override
    public CourseDTO findById(Long id) {
        return CourseMapper.toDto(courseRepository.findById(id).orElse(null));
    }

    @Override
    public void updateCourse(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).orElse(null);
        course.setName(courseDTO.getName());
        course.setESPB(courseDTO.getEspb());
        course.setSylabus(courseDTO.getSylabus());
        courseRepository.save(course);
    }

    @Override
    public CourseResponseDTO getAll(Integer page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Course> courses = courseRepository.findAll(pageable);
        return new CourseResponseDTO(CourseMapper.toDtoList(courses.getContent()), courses.getTotalPages());





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
