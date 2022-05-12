package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Performance findFirstByCourse(Course course);

    @Query(value = "select * from performance join performance_teacher_relationship where " +
            "performance.id = performance_teacher_relationship.performance_id and " +
            "performance_teacher_relationship.teacher_id = ?1",
            nativeQuery = true)
    List<Performance> findAllByTeacher(Long teacherId);
}
