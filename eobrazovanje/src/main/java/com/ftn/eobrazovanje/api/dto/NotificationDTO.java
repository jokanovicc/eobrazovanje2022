package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.CoursePerformance;
import com.ftn.eobrazovanje.model.Teacher;
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
}
