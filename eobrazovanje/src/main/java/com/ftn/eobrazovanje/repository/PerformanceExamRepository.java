package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.PerformanceExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceExamRepository extends JpaRepository<PerformanceExam, Long> {
}
