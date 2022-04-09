package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam {
    //ISPIT

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int preExamDutyPoints;

    private int finalExamPoints;

    @ManyToOne
    private Attending attending;

    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private PerformanceExam exam;

    private String status;

    public Exam(int preExamDutyPoints, int finalExamPoints,
                Attending attending, Integer grade,
                PerformanceExam exam, String status) {
        this.preExamDutyPoints = preExamDutyPoints;
        this.finalExamPoints = finalExamPoints;
        this.attending = attending;
        this.grade = grade;
        this.exam = exam;
        this.status = status;
    }
}
