package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.Attending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendingRepository extends JpaRepository<Attending, Long> {
}
