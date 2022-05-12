package com.ftn.eobrazovanje.api;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.model.ExamPeriod;
import com.ftn.eobrazovanje.service.ExamPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Validated
@RequestMapping("/api/exam-periods")
public class ExamPeriodController {

    @Autowired
    private ExamPeriodService examPeriodService;

    @GetMapping
    public ResponseEntity<List<ExamPeriod>> get(
            @RequestParam(required = false, defaultValue = "false") boolean active) {
        List<ExamPeriod> result = examPeriodService.getAll(active);
        return ResponseEntity
                .ok()
                .body(result);
    }

}
