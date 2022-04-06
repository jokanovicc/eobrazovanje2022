package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
