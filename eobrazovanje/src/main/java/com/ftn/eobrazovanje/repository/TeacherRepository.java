package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {



    @Query(value = "select * from teacher t where t.user_id not in (select ct.teacher_user_id from course_teacher ct where ct.id in (select p.teacher_id from performance_teacher_relationship p where p.performance_id = ?1))", nativeQuery = true)
    List<Teacher> getNotPerformed(Long id);

    @Query(value = "select * from teacher t where t.user_id in (select ct.teacher_user_id from course_teacher ct where ct.id in (select p.teacher_id from performance_teacher_relationship p where p.performance_id = ?1))", nativeQuery = true)
    List<Teacher> getPerformed(Long id);

    @Query(value = "select p.teacher_id from performance_teacher_relationship p where p.performance_id = ?1", nativeQuery = true)
    List<Long> getTeachers(Long id);
}
