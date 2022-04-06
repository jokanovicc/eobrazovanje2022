package com.ftn.eobrazovanje.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudyingInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int startSchoolYear;

    @ManyToOne
    private Student student;

    private String financialType;

    @ManyToOne
    private StudyProgram studyProgram;

    private int schoolYear;

}
