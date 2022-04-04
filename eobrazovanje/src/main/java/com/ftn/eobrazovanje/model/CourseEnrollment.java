package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseEnrollment {
    //izvodjenje predmeta

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer schoolYear;

    @ManyToOne
    private CourseTeacher courseTeacher;

    @ManyToOne
    private Course course;
}
