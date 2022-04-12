package com.ftn.eobrazovanje.repository;


import com.ftn.eobrazovanje.model.Student;
import com.ftn.eobrazovanje.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findFirstByReferenceNumber(String referenceNumber);

    Student findFirstByPasswordToken(String token);

    Student findFirstByIndexNumber(String index);


    @Query(value = "SELECT * FROM student join attending on student.user_id = attending.student_user_id join" +
            " performance on attending.performance_id = performance.id where performance.id =?",nativeQuery = true)
    List<Student> findByPerformanceId(Long id);

}
