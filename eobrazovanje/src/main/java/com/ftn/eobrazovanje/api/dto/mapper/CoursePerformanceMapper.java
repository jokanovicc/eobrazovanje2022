package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CoursePerformanceDTO;
import com.ftn.eobrazovanje.model.CoursePerformance;

public class CoursePerformanceMapper {

    public static CoursePerformanceDTO toDto(CoursePerformance coursePerformance) {
        return new CoursePerformanceDTO(coursePerformance.getId(),
                coursePerformance.getSchoolYear(),
                CourseTeacherMapper.toDtoList(coursePerformance.getCourseTeachers()),
                CourseMapper.toDto(coursePerformance.getCourse()));
    }

    public static CoursePerformance toEntity(CoursePerformanceDTO dto) {
        return new CoursePerformance(
                dto.getId(),
                dto.getSchoolYear(),
                CourseTeacherMapper.toEntityList(dto.getCourseTeachers()),
                CourseMapper.toEntity(dto.getCourse())
        );
    }
}
