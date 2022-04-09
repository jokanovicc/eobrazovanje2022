package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.TeacherRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRoleRepository extends JpaRepository<TeacherRole, Long> {

    TeacherRole findByName(String name);
}
