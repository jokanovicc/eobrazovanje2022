package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.StudentDTO;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

    StudentDTO findOneByReferenceNumber(String referenceNumber);

    void createFromCSV(MultipartFile file);
}
