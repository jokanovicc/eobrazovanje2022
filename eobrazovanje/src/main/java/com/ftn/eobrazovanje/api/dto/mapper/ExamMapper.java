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
                CourseMapper.toDto(exam.getAttending().getPerformance().getCourse()),
                exam.getGrade(),
                exam.getExam().getDate(),
                exam.getExam().getClassroom(),
                exam.getStatus()
        );
    }

    public static List<ExamDTO> toDtoList(List<Exam> exams) {
        return exams.stream().map(exam -> toDto(exam)).collect(Collectors.toList());
    }
}
