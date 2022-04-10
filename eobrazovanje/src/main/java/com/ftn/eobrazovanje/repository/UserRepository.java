package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String username);

    @Query(value = "SELECT * FROM user u join exam_registration a on u.id = a.student_user_id join exam e on a.exam_id = e.id WHERE e.id=?1 ", nativeQuery = true)
    List<User> findAllRegisteredToExamStudents(Long examId);


}
