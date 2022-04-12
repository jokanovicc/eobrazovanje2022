package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Performance findFirstByCourse(Course course);
}
