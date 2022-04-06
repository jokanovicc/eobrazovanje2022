package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoursePerformanceDTO {

    private Long id;
    private int schoolYear;
    private List<CourseTeacherDTO> courseTeachers;
    private CourseDTO course;
}
