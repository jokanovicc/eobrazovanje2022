package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.PerformanceExamResponse;
import com.ftn.eobrazovanje.model.PerformanceExam;

public class PerformanceExamMapper {

    public static PerformanceExamResponse toDto(PerformanceExam exam) {
        return new PerformanceExamResponse(
                exam.getId(),
                CoursePerformanceMapper.toDto(exam.getPerformance()),
                exam.getDate(),
                exam.getExamPeriod(),
                exam.getClassroom()
        );
    }
}
