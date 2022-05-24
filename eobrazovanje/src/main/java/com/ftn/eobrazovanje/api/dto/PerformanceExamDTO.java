package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.PerformanceExam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerformanceExamDTO {

    private Long id;
    private LocalDate date;
    private String name;
    private String classroom;
    private String examPeriod;
    private String status;


    public PerformanceExamDTO(PerformanceExam performanceExam){
        this.id = performanceExam.getId();
        this.date = performanceExam.getDate();
        this.name = performanceExam.getPerformance().getCourse().getName();
        this.classroom = performanceExam.getClassroom();
        this.examPeriod = performanceExam.getExamPeriod().getName();
        this.status = performanceExam.getStatus();
    }

    public static List<PerformanceExamDTO> convertToDtoList(List<PerformanceExam> performanceExams){

        List<PerformanceExamDTO> performanceExamDTOS = new ArrayList<>();
        for (PerformanceExam p: performanceExams) {
            performanceExamDTOS.add(new PerformanceExamDTO(p));

        }
        return performanceExamDTOS;
    }

}
