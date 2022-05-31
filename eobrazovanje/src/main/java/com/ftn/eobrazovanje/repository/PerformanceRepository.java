package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Course;
import com.ftn.eobrazovanje.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    Performance findFirstByCourse(Course course);

    @Query(value = "select * from performance p join performance_teacher_relationship ptr on p.id = ptr.performance_id join course_teacher ct on ptr.teacher_id = ct.id and ct.teacher_user_id = ?1",
            nativeQuery = true)
    List<Performance> findAllByTeacher(Long teacherId);

    @Query(value = "select * from performance p join course c on p.course_id = c.id join study_program_courses spc on c.id = spc.courses_id join study_program sp on sp.id = spc.study_program_id", nativeQuery = true)
    List<Performance> findAllByPerf();

    @Query(value = "select sp.name from study_program sp inner join study_program_courses spr on sp.id = spr.study_program_id inner join performance p where p.course_id = spr.courses_id and p.id = ?1", nativeQuery = true)
    String getStudyProgramName(Long perfId);
}

