package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.ExamPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamPeriodRepository extends JpaRepository<ExamPeriod, Long> {
    @Query(value = "SELECT * FROM exam_period where id = ?1", nativeQuery = true)
    Optional<ExamPeriod> findById(Long periodId);
}
