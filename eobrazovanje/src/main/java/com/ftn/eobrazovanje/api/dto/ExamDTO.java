package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.ExamStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int preExamDutyPoints;

    private int finalExamPoints;

    private CourseDTO course;

    private Integer grade;

    private LocalDate date;

    private String classroom;

    private String status;
}
