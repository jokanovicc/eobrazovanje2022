package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.CoursePerformanceDTO;
import com.ftn.eobrazovanje.api.dto.CreateCoursePerformanceRequest;
import com.ftn.eobrazovanje.api.dto.TeacherToAttendingDTO;
import com.ftn.eobrazovanje.model.CourseTeacher;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Teacher;
import com.ftn.eobrazovanje.service.PerformanceService;
import com.ftn.eobrazovanje.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin
@RestController
@RequestMapping("/api/performances")
public class CoursePerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/{performanceId}/teachers")
    public ResponseEntity addTeacherToPerformance(
            @PathVariable("performanceId") Long performanceId,
            @RequestBody TeacherToAttendingDTO teacherToAttendingDTO
    ){
        Performance performance = performanceService.findById(performanceId);
        if(performance == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Teacher teacher = teacherService.findById(teacherToAttendingDTO.getTeacherId());
        if(teacher == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        performanceService.addTeacherToPerformance(teacher, teacherToAttendingDTO.getTeacherRole(), performance);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{performanceId}/teachers/{teacherId}")
    public ResponseEntity removeTeacherFromPerformance(
            @PathVariable("performanceId") Long performId,
            @PathVariable("teacherId") Long teacherId
    ){
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity createCoursePerformance(
            @RequestBody CreateCoursePerformanceRequest coursePerformance
    ) throws URISyntaxException {
        CoursePerformanceDTO result = performanceService.create(coursePerformance);

        return ResponseEntity
                .created(new URI("/api/performances/" + result.getId()))
                .body(result);
    }
    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping
    public ResponseEntity get(Authentication authentication) {
        return ResponseEntity.ok(performanceService.get(authentication));
    }
}
