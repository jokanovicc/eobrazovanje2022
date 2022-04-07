package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private CourseService courseService;

    @GetMapping
    public List<CourseDTO> getCourses(Authentication authentication) {
      //  return courseService.getCoursesForUser(authentication);
        return null;
    }
}
