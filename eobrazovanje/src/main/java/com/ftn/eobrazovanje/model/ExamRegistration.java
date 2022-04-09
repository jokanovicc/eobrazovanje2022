package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamRegistration {
    //prijava ispita

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    @ManyToOne
    private ExamPeriod examPeriod;

    public ExamRegistration(Exam exam, Student student, ExamPeriod examPeriod) {
        this.exam = exam;
        this.student = student;
        this.examPeriod = examPeriod;
    }
}
