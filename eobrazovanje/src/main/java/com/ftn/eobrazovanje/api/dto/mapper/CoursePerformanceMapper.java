package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CoursePerformanceDTO;
import com.ftn.eobrazovanje.model.CoursePerformance;

public class CoursePerformanceMapper {

    public static CoursePerformanceDTO toDto(CoursePerformance coursePerformance) {
        return new CoursePerformanceDTO(coursePerformance.getId(),
                coursePerformance.getSchoolYear(),
                CourseTeacherMapper.toDto(coursePerformance.getCourseTeacher()),
                CourseMapper.toDto(coursePerformance.getCourse()));
    }

    public static CoursePerformance toEntity(CoursePerformanceDTO dto) {
        return new CoursePerformance(
                dto.getId(),
                dto.getSchoolYear(),
                CourseTeacherMapper.toEntity(dto.getCourseTeacher()),
                CourseMapper.toEntity(dto.getCourse())
        );
    }
}
