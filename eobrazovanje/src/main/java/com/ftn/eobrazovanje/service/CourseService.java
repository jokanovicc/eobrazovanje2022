package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getCoursesForUser(Authentication authentication);
}
