package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.ExamDTO;
import com.ftn.eobrazovanje.model.Exam;

import java.util.List;
import java.util.stream.Collectors;

public class ExamMapper {

    public static ExamDTO toDto(Exam exam) {
        return new ExamDTO(
                exam.getId(),
                exam.getPreExamDutyPoints(),
                exam.getFinalExamPoints(),
                CourseMapper.toDto(exam.getAttending().getCoursePerformance().getCourse()),
                exam.getGrade(),
                exam.getDate(),
                exam.getClassroom(),
                exam.getStatus().name()
        );
    }

    public static List<ExamDTO> toDtoList(List<Exam> exams) {
        return exams.stream().map(exam -> toDto(exam)).collect(Collectors.toList());
    }
}
