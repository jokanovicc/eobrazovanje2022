package com.ftn.eobrazovanje.api.dto;

import com.ftn.eobrazovanje.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDTO {

    private Long id;
    private String name;
    private String jmbg;
    private String address;
    private String username;
    private String gender;
}
