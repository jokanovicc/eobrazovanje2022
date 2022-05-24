package com.ftn.eobrazovanje.repository;

import com.ftn.eobrazovanje.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface FileDBRepository extends JpaRepository<FileDB, Long> {

    List<FileDB> findAllByStudent_Id(Long id);



}
