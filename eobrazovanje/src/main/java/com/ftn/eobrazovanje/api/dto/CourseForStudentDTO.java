package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.CourseTeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseForStudentDTO extends CourseDTO{
    private List<CourseTeacher> teachers;
    private int preExamDutyPoints;
    private int finalExamPoints;

    public CourseForStudentDTO(Long id,
                               String name,
                               String sylabus,
                               String ESPB,
                               List<CourseTeacher> teachers,
                               int preExamDutyPoints,
                               int finalExamPoints
    ) {
        super(name,id, sylabus, ESPB);
        this.teachers = teachers;
        this.preExamDutyPoints = preExamDutyPoints;
        this.finalExamPoints = finalExamPoints;
    }
}
