package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CourseTeacherDTO;
import com.ftn.eobrazovanje.model.CourseTeacher;

public class CourseTeacherMapper {
    public static CourseTeacherDTO toDto(CourseTeacher teacher) {
        return new CourseTeacherDTO(
                teacher.getId(),
                teacher.getTeacherRole().getName(),
                teacher.getTeacher().getUser().getName(),
                teacher.getTeacher().getUser().getUsername()
        );
    }

    public static CourseTeacher toEntity(CourseTeacherDTO teacher) {
        return new CourseTeacher(teacher.getId());
    }
}
