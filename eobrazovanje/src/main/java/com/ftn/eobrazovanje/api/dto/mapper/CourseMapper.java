package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.model.Course;

public class CourseMapper {
    public static CourseDTO toDto(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getSylabus(),
                course.getESPB()
        );
    }

    public static Course toEntity(CourseDTO dto) {
        return new Course(
                dto.getId(),
                dto.getSylabus(),
                dto.getESPB()
        );
    }
}
