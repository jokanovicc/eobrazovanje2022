package com.ftn.eobrazovanje.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SVFormDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String jmbg;
    @NotBlank
    private String address;
    @NotBlank
    private String gender;
    @NotBlank
    private String index;

}
