package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.api.dto.ExamWithStudentInfoResponse;
import com.ftn.eobrazovanje.api.dto.GradeStudentRequest;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
    public ResponseEntity<List<ExamDTO>> getExams(
            @RequestParam(name = "status", required = false, defaultValue = "ALL") String status,
            Authentication authentication
    ) {
        List<ExamDTO> result = examService.getExamsForStudent(authentication, status);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    @PostMapping("/{performanceExamId}/attending/{attendingId}")
    public ResponseEntity<ExamDTO> registerExam(
            @PathVariable Long performanceExamId,
            @PathVariable Long attendingId,
            Authentication authentication
    ) throws URISyntaxException {

        ExamDTO response = examService.registerExam(performanceExamId, attendingId, authentication);

        return ResponseEntity
                .created(new URI("/api/exams/" + response.getId()))
                .body(response);
    }

    @PreAuthorize("hasRole('ADMIN', 'TEACHER')")
    @PutMapping("/{examId}/students/{studentId}")
    public ResponseEntity<ExamDTO> gradeStudent(
            @PathVariable Long examId,
            @PathVariable Long studentId,
            @RequestBody(required = true) GradeStudentRequest studentGrade,
            Authentication authentication
    ) {
        ExamDTO result = examService.gradeStudent(examId, studentId, studentGrade, authentication);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("/{examId}/students")
    public ResponseEntity<List<ExamWithStudentInfoResponse>> findRegisteredToExamStudents(
            @PathVariable("examId") Long performanceExamId
    ){
        List<ExamWithStudentInfoResponse> response = examService.getStudentsWhoRegisteredExam(performanceExamId);
        return ResponseEntity
                .ok()
                .body(response);
    }


}
