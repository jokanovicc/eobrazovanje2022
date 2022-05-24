package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.CourseTeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseTeacherDTO {

    private Long id;
    private String teacherRole;
    private String name;
    private String username;

    public CourseTeacherDTO(CourseTeacher ct){
        this.id = ct.getId();
        this.teacherRole = ct.getTeacherRole().getName();
        this.name = ct.getTeacher().getUser().getName() + ct.getTeacher().getUser().getLastname();
        this.username = ct.getTeacher().getUser().getUsername();
    }

}
