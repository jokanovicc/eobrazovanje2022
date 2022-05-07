package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "select * from exam join exam_registration on " +
            "exam_registration.exam_id = exam.id " +
            "where exam_registration.student_user_id = ?1 " +
            " and exam.status = 'PASSED'",
            nativeQuery = true)
    List<Exam> findPassedByStudentId(Long studentId);

    @Query(value = "select * from exam join exam_registration on " +
            "exam_registration.exam_id = exam.id " +
            "where exam_registration.student_user_id = ?1 " +
            " and exam.status = 'REGISTERED'",
            nativeQuery = true)
    List<Exam> findRegisteredByStudentId(Long studentId);

    @Query(value = "select * from exam join exam_registration on " +
            "exam_registration.exam_id = exam.id " +
            "where exam_registration.student_user_id = ?1 " +
            " and exam.status = 'FAILED'",
            nativeQuery = true)
    List<Exam> findFailedByStudentId(Long studentId);

    @Query(value = "select * from exam join exam_registration on " +
            "exam_registration.exam_id = exam.id " +
            "where exam_registration.student_user_id = ?1",
            nativeQuery = true)
    List<Exam> findAllByStudentId(Long studentId);

    @Query(value = "select * from exam where attending_id = ?1 and status in ('FAILED', 'PASSED', 'REGISTERED')",
            nativeQuery = true)
    List<Exam> findByAttending(Long attendingId);

    @Query(value = "select * from exam where exam_id = ?1 and status = 'REGISTERED'",
            nativeQuery = true)
    List<Exam> findAllRegisteredForPerformanceExam(Long performanceExamId);

    @Query(value = "select * from exam e join performance_exam pe on e.exam_id = pe.id join performance p on pe.performance_id = p.id join performance_teacher_relationship ptr on " +
            " p.id = ptr.performance_id and ptr.teacher_id = ?1", nativeQuery = true)
    List<Exam> findAllExamsByTeacher(Long teacherId);
}
