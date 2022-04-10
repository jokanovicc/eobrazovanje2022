package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.*;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ExamService {

    List<ExamDTO> getExamsForStudent(Authentication authentication, String status);
    ExamDTO registerExam(Long performanceExamId, Long attendingId, Authentication authentication);
    ExamDTO gradeStudent(Long examId, Long studentId, GradeStudentRequest studentGrade, Authentication authentication);
    List<ExamWithStudentInfoResponse> getStudentsWhoRegisteredExam(Long performanceExamId);
    PerformanceExamResponse createExam(CreateExamRequest request);
}
