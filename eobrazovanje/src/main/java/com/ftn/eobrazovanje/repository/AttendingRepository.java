package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Attending;
import com.ftn.eobrazovanje.model.Performance;
import com.ftn.eobrazovanje.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendingRepository extends JpaRepository<Attending, Long> {


    Attending findFirstByStudentAndPerformance(Student student, Performance performance);
}
