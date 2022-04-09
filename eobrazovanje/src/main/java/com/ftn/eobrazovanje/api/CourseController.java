package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.CreateCourseDTO;
import com.ftn.eobrazovanje.api.dto.mapper.CourseMapper;
import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

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
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") Long id){
        Course course = courseService.findById(id);
        courseService.updateCourse(course, courseDTO);

        return CourseMapper.toDto(course);

    }
}
