package com.ftn.eobrazovanje.service;

import com.ftn.eobrazovanje.api.dto.StudentDTO;

public interface StudentService {

    StudentDTO findOneByReferenceNumber(String referenceNumber);
}
