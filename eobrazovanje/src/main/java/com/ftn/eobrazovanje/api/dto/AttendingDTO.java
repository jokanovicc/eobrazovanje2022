package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendingDTO {
    private Long id;

    private CoursePerformanceDTO performance;

    private StudentDTO student;

}
