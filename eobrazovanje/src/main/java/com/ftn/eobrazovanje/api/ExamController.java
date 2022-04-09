package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.api.dto.mapper.StudentMapper;
import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.model.User;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<ExamDTO> getExams(
            @RequestParam(name = "status", required = false, defaultValue = "ALL") String status,
            Authentication authentication
    ) {
        return examService.getExamsForStudent(authentication, status);
    }

    @PostMapping("/{performanceExamId}/attending/{attendingId}/period/{periodId}")
    public ExamDTO registerExam(
            @PathVariable Long performanceExamId,
            @PathVariable Long attendingId,
            @PathVariable Long periodId,
            Authentication authentication
    ) {
        return examService.registerExam(performanceExamId, attendingId, periodId, authentication);
    }

    @GetMapping("/{examId}/students")
    public List<StudentDTO> findRegisteredToExamStudents(@PathVariable("examId") Long examId){
        return studentService.findExamRegisteredStudents(examId);
    }
}
