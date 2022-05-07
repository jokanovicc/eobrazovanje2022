package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.PerformanceExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceExamRepository extends JpaRepository<PerformanceExam, Long> {

    @Query(value = "select * from performance_exam join performance join attending join exam on " +
            "performance_exam.performance_id = performance.id and " +
            "performance.id = attending.performance_id " +
            "where attending.student_user_id = ?1 " +
            "and attending.id not in (select attending_id from exam where status = 'PASSED' or status = 'REGISTERED') " +
            "group by performance_exam.id", nativeQuery = true)
    List<PerformanceExam> getByStudentId(Long studentId);

    @Query(value = "select pe.id,pe.classroom,pe.date,pe.exam_period_id,pe.performance_id from performance_exam pe join performance p on pe.performance_id = p.id join performance_teacher_relationship ptr on p.id = ptr.performance_id and ptr.teacher_id = ?1", nativeQuery = true)
    List<PerformanceExam> findExamPeriodByTeacher(Long teacherId);
}
