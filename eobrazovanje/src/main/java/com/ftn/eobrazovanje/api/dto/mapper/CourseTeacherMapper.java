package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CourseTeacherDTO;
import com.ftn.eobrazovanje.model.CourseTeacher;

import java.util.List;
import java.util.stream.Collectors;

public class CourseTeacherMapper {
    public static CourseTeacherDTO toDto(CourseTeacher teacher) {
        return new CourseTeacherDTO(
                teacher.getId(),
                teacher.getTeacherRole().getName(),
                teacher.getTeacher().getUser().getName(),
                teacher.getTeacher().getUser().getUsername()
        );
    }

    public static List<CourseTeacherDTO> toDtoList(List<CourseTeacher> teachers) {
        return teachers
                .stream()
                .map(teacher -> CourseTeacherMapper.toDto(teacher))
                .collect(Collectors.toList());
    }

    public static CourseTeacher toEntity(CourseTeacherDTO teacher) {
        return new CourseTeacher(teacher.getId());
    }

    public static List<CourseTeacher> toEntityList(List<CourseTeacherDTO> teachers) {
        return teachers
                .stream()
                .map(teacher -> toEntity(teacher))
                .collect(Collectors.toList());
    }
}
