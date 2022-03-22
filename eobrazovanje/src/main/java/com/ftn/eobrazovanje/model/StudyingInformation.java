package com.ftn.eobrazovanje.model;

import com.ftn.eobrazovanje.model.roles.Student;
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
    private Integer id;
    private int yearOfEnrollment;

    @OneToOne
    private Student student;
    private boolean bugdet;
    private int yearOfStudying;

    @ManyToOne
    private StudyingProgramme programme;

}
