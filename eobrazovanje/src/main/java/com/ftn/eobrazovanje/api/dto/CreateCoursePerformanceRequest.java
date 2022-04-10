package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCoursePerformanceRequest {
    @NotNull
    @NotBlank
    private Long courseId;
    @NotNull
    private Integer schoolYear;

    private List<CourseTeacherRequest> teachers;
}
