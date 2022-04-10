package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.ExamPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerformanceExamResponse {

    private Long id;

    private CoursePerformanceDTO performance;

    private LocalDate date;

    private ExamPeriod examPeriod;

    private String classroom;
}
