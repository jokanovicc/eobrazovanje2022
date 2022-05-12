package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "select * from notification where performance_id in" +
            "(select performance_id from attending where student_user_id = ?1)",
            nativeQuery = true)
    Page<Notification> getOfStudent(Long studentId, Pageable pageable);

    @Query(value = "select * from notification where teacher_user_id = ?1",
            nativeQuery = true)
    Page<Notification> getOfTeacher(Long teacherId, Pageable pageable);

    @Query(value = "select * from notification where id = ?1 ORDER BY ?#{#pageable}", nativeQuery = true)
    Optional<Notification> getById(Long id);

}
