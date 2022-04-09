package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CoursePerformanceDTO;
import com.ftn.eobrazovanje.model.Performance;

public class CoursePerformanceMapper {

    public static CoursePerformanceDTO toDto(Performance coursePerformance) {
        return new CoursePerformanceDTO(coursePerformance.getId(),
                coursePerformance.getSchoolYear(),
                coursePerformance.getCourseTeachers() != null ?
                        CourseTeacherMapper.toDtoList(coursePerformance.getCourseTeachers())
                : null,
                coursePerformance.getCourse() != null ?
                        CourseMapper.toDto(coursePerformance.getCourse())
                : null);
    }

    public static Performance toEntity(CoursePerformanceDTO dto) {
        return new Performance(
                dto.getId(),
                dto.getSchoolYear(),
                CourseTeacherMapper.toEntityList(dto.getCourseTeachers()),
                CourseMapper.toEntity(dto.getCourse())
        );
    }
}
