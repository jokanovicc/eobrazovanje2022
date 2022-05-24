package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
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

    @Query(value = "SELECT * FROM ssluzba.course join performance on course.id = performance.course_id join " +
            "attending on performance.id = attending.performance_id join exam on attending.id = exam.attending_id where exam_id = ?",
            nativeQuery = true)
    Course findByExamId(Long examId);
}
