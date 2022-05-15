package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.CourseResponseDTO;
import com.ftn.eobrazovanje.api.dto.CreateCourseDTO;
import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getCoursesForUser(Authentication authentication);

    Course createCourse(Course course);

    CourseDTO findById(Long id);

    void updateCourse(Long id, CourseDTO courseDTO);

    CourseResponseDTO getAll(Integer pageNo);
}
