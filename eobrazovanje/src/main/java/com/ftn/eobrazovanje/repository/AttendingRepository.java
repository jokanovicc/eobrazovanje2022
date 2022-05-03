package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Attending;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendingRepository extends JpaRepository<Attending, Long> {

    Attending findFirstByStudentAndPerformance(Student student, Performance performance);

    @Query(value = "select * from attending where student_user_id = ?1 and performance_id = ?2",
            nativeQuery = true)
    List<Attending> findByStudentAndPerformance(Long studentId, Long performanceId);
}
