package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.api.dto.ExamWithStudentInfoResponse;
import com.ftn.eobrazovanje.api.dto.GradeStudentRequest;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@Validated
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

    @PostMapping("/{performanceExamId}/attending/{attendingId}")
    public ExamDTO registerExam(
            @PathVariable Long performanceExamId,
            @PathVariable Long attendingId,
            Authentication authentication
    ) {
        return examService.registerExam(performanceExamId, attendingId, authentication);
    }

    @PutMapping("/{examId}/students/{studentId}")
    public ExamDTO gradeStudent(
            @PathVariable Long examId,
            @PathVariable Long studentId,
            @RequestBody(required = true) GradeStudentRequest studentGrade,
            Authentication authentication
    ) {
        return examService.gradeStudent(examId, studentId, studentGrade, authentication);
    }

    @GetMapping("/{examId}/students")
    public List<ExamWithStudentInfoResponse> findRegisteredToExamStudents(
            @PathVariable("examId") Long performanceExamId
    ){
        return examService.getStudentsWhoRegisteredExam(performanceExamId);
    }


}
