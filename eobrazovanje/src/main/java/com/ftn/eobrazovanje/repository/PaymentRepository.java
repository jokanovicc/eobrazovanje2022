package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


    @Query(value = "SELECT * FROM payment where student_user_id = ?", nativeQuery = true)
    List<Payment> findAllByStudentId(Long id);
}
