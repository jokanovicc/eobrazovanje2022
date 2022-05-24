package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamWithStudentInfoResponse {

    private Long examId;

    private int preExamDutyPoints;

    private int finalExamPoints;

    private Integer grade;

    private String status;

    private String studentName;

    private String studentIndex;

    private Long studentId;
}
