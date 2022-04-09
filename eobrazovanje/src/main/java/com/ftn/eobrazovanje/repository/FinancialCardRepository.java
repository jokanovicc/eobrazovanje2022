package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.FinancialCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinancialCardRepository extends JpaRepository<FinancialCard, Long> {

    @Query(value = "select * from financial_card where student_user_id = ?1",
            nativeQuery = true)
    Optional<FinancialCard> findByStudentId(Long studentId);
}
