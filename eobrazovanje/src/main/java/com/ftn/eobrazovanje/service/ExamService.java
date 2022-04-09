package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.model.Exam;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ExamService {
    List<ExamDTO> getExamsForStudent(Authentication authentication, String status);
    ExamDTO registerExam(Long performanceExamId, Long examId, Long attendingId, Authentication authentication);
}
