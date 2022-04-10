package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.ExamWithStudentInfoResponse;
import com.ftn.eobrazovanje.model.Exam;

import java.util.List;
import java.util.stream.Collectors;

public class ExamWithStudentInfoMapper {

    public static ExamWithStudentInfoResponse toDto(Exam exam) {
        return new ExamWithStudentInfoResponse(exam.getId(),
                exam.getPreExamDutyPoints(),
                exam.getFinalExamPoints(),
                exam.getGrade(),
                exam.getStatus(),
                exam.getAttending().getStudent().getUser().getName() + " " +
                        exam.getAttending().getStudent().getUser().getLastname(),
                exam.getAttending().getStudent().getIndexNumber()
        );
    }

    public static List<ExamWithStudentInfoResponse> toDtoList(List<Exam> exams) {
        return exams.stream().map(exam -> toDto(exam)).collect(Collectors.toList());
    }
}
