package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.api.dto.mapper.CourseTeacherMapper;
import com.ftn.eobrazovanje.model.CourseTeacher;
import com.ftn.eobrazovanje.model.Performance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceTeachersDTO {

    private Long id;
    private String courseName;
    private String courseSylabus;
    private int schoolYear;
    private List<CourseTeacherDTO> courseTeacher = new ArrayList<>();


    public PerformanceTeachersDTO(Performance performance){
        this.id = performance.getId();
        this.courseName = performance.getCourse().getName();
        this.courseSylabus = performance.getCourse().getSylabus();
        this.schoolYear = performance.getSchoolYear();
    }
}
