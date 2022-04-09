package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.CourseTeacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Long> {

    CourseTeacher findByTeacher_Id(Long id);

}
