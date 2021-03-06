package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerformanceExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "exam_period_id")
    private ExamPeriod examPeriod;

    private String classroom;

    private String status;

    public PerformanceExam(Performance performance, LocalDate date, ExamPeriod examPeriod, String classroom, String status) {
        this.performance = performance;
        this.date = date;
        this.examPeriod = examPeriod;
        this.classroom = classroom;
        this.status = status;
    }
}
