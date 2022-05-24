package com.ftn.eobrazovanje.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String jmbg;
    @NotBlank
    private String address;
    @NotBlank
    private String username;
    private String gender;
    @NotBlank
    private String password;

    @NotBlank
    private String email;


    public TeacherDTO(Long id) {
        this.id = id;
    }


}
