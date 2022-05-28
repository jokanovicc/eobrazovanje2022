package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.*;
import com.ftn.eobrazovanje.model.Exam;
import com.ftn.eobrazovanje.model.ExamPeriod;
import com.ftn.eobrazovanje.model.PerformanceExam;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ExamService {

    List<ExamDTO> getExamsForStudent(Authentication authentication, String status);
    ExamDTO registerExam(Long performanceExamId, Long attendingId, Authentication authentication);

    List<ExamDTO> findExamByTeacher(Authentication authentication);
    ExamDTO gradeStudent(Long examId, Long studentId, GradeStudentRequest studentGrade, Authentication authentication);
    List<Exam> findExamsByPerformanceExam(Long id);
    List<ExamWithStudentInfoResponse> getStudentsWhoRegisteredExam(Long performanceExamId);
    PerformanceExamResponse createExam(CreateExamRequest request);
    List<PerformanceExamResponse> getExamsForRegistration(Authentication authentication, Long examPeriodId);
    List<PerformanceExamDTO> getExamPeriodsByTeacher(Authentication authentication);
    PerformanceExamResponse publishPreliminaryResults(Authentication authentication,Long examId, PerformanceExamDTO performanceExamDTO);
    PerformanceExamResponse finalResults(Long examId, PerformanceExamDTO performanceExamDTO);
    void deregisterExam(Long examId);
}
