package com.ftn.eobrazovanje.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseDTO {

    private String name;
    private String espb;
    private String sylabus;

}
