package com.ftn.eobrazovanje.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDTO {

    private Long id;
    private String name;
    private String lastname;
    private String jmbg;
    private String address;
    private String username;
    private String gender;
    private String password;


    public TeacherDTO(Long id) {
        this.id = id;
    }


}
