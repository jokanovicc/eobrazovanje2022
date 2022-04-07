package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "select * course join course_performance join attending on" +
            "course.id = course_performance.course_id," +
            "course_performance.id = attending.course_performance_id" +
            "where attending.student_user_id = ?1",
            nativeQuery = true)
     List<Course> getByStudentId(Long studentId);

    @Query(value = "select * course join course_performance join course_performance_course_teachers on" +
            "course.id = course_performance.course_id, " +
            "course_performance.id = course_performance_course_teachers.course_performance_id" +
            "where course_performance_course_teachers.course_teachers_id = ?1",
            nativeQuery = true)
    List<Course> getByTeacherId(Long teacherId);
}
