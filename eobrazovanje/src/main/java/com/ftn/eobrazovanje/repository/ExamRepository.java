package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "select * from exam_registration join exam on" +
            "exam_registration.exam_id == exam_registration.id " +
            "where exam_registration.student_user_id = ?1 " +
            "and exam.status = 'PASSED'",
            nativeQuery = true)
    List<Exam> findPassedByStudentId(Long studentId);

    @Query(value = "select * from exam_registration join exam on" +
            "exam_registration.exam_id == exam_registration.id " +
            "where exam_registration.student_user_id = ?1 " +
            "and exam.status = 'REGISTERED'",
            nativeQuery = true)
    List<Exam> findRegisteredByStudentId(Long studentId);

    @Query(value = "select * from exam_registration join exam on" +
            "exam_registration.exam_id == exam_registration.id " +
            "where exam_registration.student_user_id = ?1 " +
            "and exam.status = 'FAILED'",
            nativeQuery = true)
    List<Exam> findFailedByStudentId(Long studentId);

    @Query(value = "select * from exam_registration join exam on" +
            "exam_registration.exam_id == exam_registration.id " +
            "where exam_registration.student_user_id = ?1 ",
            nativeQuery = true)
    List<Exam> findAllByStudentId(Long studentId);
}
