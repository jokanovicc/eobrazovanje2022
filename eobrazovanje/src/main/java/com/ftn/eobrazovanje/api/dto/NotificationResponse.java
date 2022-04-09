package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationResponse {

    private Long id;
    private CoursePerformanceDTO coursePerformance;
    private TeacherDTO teacher;
    private String message;

    public NotificationResponse(CoursePerformanceDTO coursePerformance, TeacherDTO teacher, String message) {
        this.coursePerformance = coursePerformance;
        this.teacher = teacher;
        this.message = message;
    }
}
