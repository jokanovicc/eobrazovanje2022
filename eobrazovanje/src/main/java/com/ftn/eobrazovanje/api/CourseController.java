package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.CourseResponseDTO;
import com.ftn.eobrazovanje.api.dto.NotificationResponse;
import com.ftn.eobrazovanje.api.dto.TeacherToAttendingDTO;
import com.ftn.eobrazovanje.api.dto.mapper.CourseMapper;
import com.ftn.eobrazovanje.model.*;
import com.ftn.eobrazovanje.service.CourseService;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<CourseDTO> getCourses(Authentication authentication) {
        return courseService.getCoursesForUser(authentication);
    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO){
        Course course = CourseMapper.toEntity(courseDTO);
        courseService.createCourse(course);
        return courseDTO;
    }

    @PutMapping("/{id}")
    public void updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") Long id){
        CourseDTO course = courseService.findById(id);
        courseService.updateCourse(course.getId(), courseDTO);

    }
    @GetMapping("/all")
    public CourseResponseDTO getAllCourses(
            @RequestParam(defaultValue = "0") Integer page) {
        return courseService.getAll(page);
    }

    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id){
        System.out.println("sdsaas");
        return courseService.findById(id);


    }


}
