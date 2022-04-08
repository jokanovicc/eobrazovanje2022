package com.ftn.eobrazovanje.api.dto;

//TODO zavrsiti

import com.ftn.eobrazovanje.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {

    private Long id;

    private String indexNumber;

    private User user;

    private String referenceNumber;
}
