package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @GetMapping
    public List<CourseDTO> getCourses(Authentication authentication) {
        return null;
    }
}
