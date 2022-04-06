package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "select * from notification where course_performance_id in" +
            "(select course_performance_id from attending where student_user_id = ?1)",
            nativeQuery = true)
    List<Notification> getOfStudent(Long studentId);
}
