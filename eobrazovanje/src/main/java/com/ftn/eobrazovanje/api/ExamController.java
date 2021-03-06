package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.*;
import com.ftn.eobrazovanje.exception.BadRequestException;
import com.ftn.eobrazovanje.model.ExamPeriod;
import com.ftn.eobrazovanje.model.PerformanceExam;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.StudentService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/registration")
    public ResponseEntity<List<PerformanceExamResponse>> getExamsForRegistration(
            @RequestParam(required = false) Long examPeriodId,
            Authentication authentication
    ) {
        return ResponseEntity
                .ok()
                .body(examService.getExamsForRegistration(authentication, examPeriodId));
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

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
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

    @PostMapping
    public ResponseEntity<PerformanceExamResponse> createExam(
            @RequestBody CreateExamRequest request
    ) throws URISyntaxException {
        PerformanceExamResponse response = examService.createExam(request);
        return ResponseEntity
                .created(new URI("/api/exams/" + response.getId()))
                .body(response);
    }

    @PutMapping("/results/{examId}")
    public ResponseEntity publishPreliminaryResults(Authentication authentication, @PathVariable Long examId,
                                                    @RequestParam(name = "resultsType") String resultsType,
                                                    @RequestBody PerformanceExamDTO performanceExamDTO){
        if(resultsType.equals("preliminary")) {
            try {
                PerformanceExamResponse responseDto = examService.publishPreliminaryResults(authentication, examId, performanceExamDTO);
                return new ResponseEntity(responseDto, HttpStatus.OK);
            }catch (BadRequestException e) {
                return new ResponseEntity("Unesite bodove studentima",HttpStatus.BAD_REQUEST);
            }
        }else{
            try {
                PerformanceExamResponse responseDto = examService.finalResults(examId, performanceExamDTO);
                return new ResponseEntity(responseDto, HttpStatus.OK);
            }catch (BadRequestException e) {
                return new ResponseEntity("Prvo morate objaviti preliminarne rezultate",HttpStatus.BAD_REQUEST);
            }
            catch (IllegalArgumentException e){
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

        }
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @GetMapping("/teacher-exams")
    public List<PerformanceExamDTO> getExamsByTeacher(Authentication authentication){
        return examService.getExamPeriodsByTeacher(authentication);
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @DeleteMapping("/exam-registrations/{examId}")
    public ResponseEntity deregisterExam(
            @PathVariable Long examId
    ) {
        examService.deregisterExam(examId);
        return ResponseEntity.noContent().build();
    }

}
