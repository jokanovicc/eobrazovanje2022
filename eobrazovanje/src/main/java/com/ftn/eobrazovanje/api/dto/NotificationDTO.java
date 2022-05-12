package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationDTO {

    private Long id;
    private CoursePerformanceDTO coursePerformance;
    private TeacherDTO teacher;
    private String message;


    public NotificationDTO(CoursePerformanceDTO coursePerformance, TeacherDTO teacher, String message) {

        this.coursePerformance = coursePerformance;
        this.teacher = teacher;
        this.message = message;
    }
}
