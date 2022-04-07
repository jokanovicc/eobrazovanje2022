package com.ftn.eobrazovanje.api.dto.mapper;

import com.ftn.eobrazovanje.api.dto.CourseDTO;
import com.ftn.eobrazovanje.api.dto.CourseForStudentDTO;
import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.Exam;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseDTO toDto(Course course) {
        return new CourseDTO(
                course.getName(),
                course.getId(),
                course.getSylabus(),
                course.getESPB()
        );
    }

    public static List<CourseDTO> toDtoList(List<Course> courses) {
        return courses.stream().map(course -> toDto(course)).collect(Collectors.toList());
    }

    public static CourseDTO toDtoFromExam(Exam exam) {
        return new CourseForStudentDTO
                (exam.getAttending().getCoursePerformance().getCourse().getId(),
                exam.getAttending().getCoursePerformance().getCourse().getName(),
                exam.getAttending().getCoursePerformance().getCourse().getSylabus(),
                exam.getAttending().getCoursePerformance().getCourse().getESPB(),
                exam.getAttending().getCoursePerformance().getCourseTeachers(),
                exam.getPreExamDutyPoints(),
                exam.getFinalExamPoints());
    }

    public static Course toEntity(CourseDTO dto) {
        return new Course(
                dto.getId(),
                dto.getName(),
                dto.getSylabus(),
                dto.getESPB()
        );
    }
}
