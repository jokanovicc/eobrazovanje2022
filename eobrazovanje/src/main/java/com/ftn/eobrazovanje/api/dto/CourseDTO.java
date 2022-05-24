package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {
    @NotBlank
    private String name;
    private Long id;
    @NotBlank
    private String sylabus;
    @NotBlank
    private String espb;
}
