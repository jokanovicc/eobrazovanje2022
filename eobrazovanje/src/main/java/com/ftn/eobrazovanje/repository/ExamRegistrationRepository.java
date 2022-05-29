package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.ExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRegistrationRepository extends JpaRepository<ExamRegistration, Long> {
    @Query(value = "select * from exam_registration where exam_id = ?1",
            nativeQuery = true)
    ExamRegistration findByExamId(Long examId);
}
