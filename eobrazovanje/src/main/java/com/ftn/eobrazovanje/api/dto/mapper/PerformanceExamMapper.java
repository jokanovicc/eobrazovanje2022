package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.PerformanceExamResponse;
import com.ftn.eobrazovanje.model.PerformanceExam;

import java.util.List;
import java.util.stream.Collectors;

public class PerformanceExamMapper {

    public static PerformanceExamResponse toDto(PerformanceExam exam) {
        return new PerformanceExamResponse(
                exam.getId(),
                CoursePerformanceMapper.toDto(exam.getPerformance()),
                exam.getDate(),
                exam.getExamPeriod(),
                exam.getClassroom(),
                exam.getStatus()
        );
    }

    public static List<PerformanceExamResponse> toDtoList(List<PerformanceExam> exams) {
        return exams.stream().map(PerformanceExamMapper::toDto).collect(Collectors.toList());
    }
}
