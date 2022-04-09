package com.ftn.eobrazovanje.repository;


import com.ftn.eobrazovanje.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findFirstByReferenceNumber(String referenceNumber);

}
