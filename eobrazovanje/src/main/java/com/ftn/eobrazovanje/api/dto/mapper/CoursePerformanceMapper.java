package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CoursePerformanceDTO;
import com.ftn.eobrazovanje.model.Performance;

import java.util.List;
import java.util.stream.Collectors;

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


    public static List<CoursePerformanceDTO> toDtoList(List<Performance> performances) {
        return performances.stream().map(CoursePerformanceMapper::toDto).collect(Collectors.toList());
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
