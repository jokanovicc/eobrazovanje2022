package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.service.ExamService;
import com.ftn.eobrazovanje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ExamDTO> getExams(
            @RequestParam(name = "status", required = false, defaultValue = "ALL") String status,
            Authentication authentication
    ) {
        return examService.getExamsForStudent(authentication, status);
    }

    @PostMapping("/performanceExam/{performanceExamId}/attending/{attendingId}/period/{periodId}")
    public ExamDTO registerExam(
            @PathVariable Long performanceExamId,
            @PathVariable Long attendingId,
            @PathVariable Long periodId,
            Authentication authentication
    ) {
        return examService.registerExam(performanceExamId, attendingId, periodId, authentication);
    }
}
