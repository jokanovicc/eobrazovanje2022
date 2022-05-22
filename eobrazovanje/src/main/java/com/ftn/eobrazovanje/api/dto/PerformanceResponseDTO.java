package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceResponseDTO {

    private Long id;
    private int schoolYear;
    private String courseName;

    private String studyProgram;


    public PerformanceResponseDTO(Performance performance){
        this.id = performance.getId();
        this.schoolYear = performance.getSchoolYear();
        this.courseName = performance.getCourse().getName();
    }

}
