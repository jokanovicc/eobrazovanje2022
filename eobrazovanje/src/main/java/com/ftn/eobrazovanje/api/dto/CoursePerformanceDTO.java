package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.CourseTeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoursePerformanceDTO {

    private Long id;
    private int schoolYear;
    private CourseTeacherDTO courseTeacher;
    private CourseDTO course;
}
