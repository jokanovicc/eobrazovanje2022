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
                course.getName() != null ? course.getName() : null,
                course.getId(),
                course.getSylabus() != null ? course.getSylabus() : null,
                course.getESPB() != null ? course.getESPB() : null
        );
    }

    public static List<CourseDTO> toDtoList(List<Course> courses) {
        return courses.stream().map(course -> toDto(course)).collect(Collectors.toList());
    }

    public static CourseDTO toDtoFromExam(Exam exam) {
        return new CourseForStudentDTO
                (exam.getAttending().getPerformance().getCourse().getId(),
                exam.getAttending().getPerformance().getCourse().getName(),
                exam.getAttending().getPerformance().getCourse().getSylabus(),
                exam.getAttending().getPerformance().getCourse().getESPB(),
                exam.getAttending().getPerformance().getCourseTeachers(),
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
