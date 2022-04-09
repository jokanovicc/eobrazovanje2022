package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
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
    public CourseDTO updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable("id") Long id){
        Course course = courseService.findById(id);
        courseService.updateCourse(course, courseDTO);

        return CourseMapper.toDto(course);

    }

    @PostMapping("/performances/{performanceId}/teachers")
    public ResponseEntity addTeacherToPerformance(@PathVariable("performanceId") Long performanceId, @RequestBody TeacherToAttendingDTO teacherToAttendingDTO){
        Performance performance = performanceService.findById(performanceId);
        if(performance == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Teacher teacher = teacherService.findById(teacherToAttendingDTO.getTeacherId());
        if(teacher == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        performanceService.addTeacherToPerformance(teacher, teacherToAttendingDTO.getTeacherRole(),teacherService,performance);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @DeleteMapping("/performances/{performanceId}/teachers/{teacherId}")
    public ResponseEntity removeTeacherFromPerformance(@PathVariable("performanceId") Long performId, @PathVariable("teacherId") Long teacherId){
        Performance performance = performanceService.findById(performId);
        if(performance == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CourseTeacher courseTeacher = teacherService.findByCourseTeacherByTeacher(teacherId);
        if(courseTeacher == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        performanceService.removeTeacherFromPerformance(performance, courseTeacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
