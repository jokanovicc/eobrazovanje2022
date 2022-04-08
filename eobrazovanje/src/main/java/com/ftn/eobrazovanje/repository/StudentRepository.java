package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.api.dto.StudentDTO;
import com.ftn.eobrazovanje.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUser_Id(Long studentId);

    Student findFirstByReferenceNumber(String referenceNumber);
}
