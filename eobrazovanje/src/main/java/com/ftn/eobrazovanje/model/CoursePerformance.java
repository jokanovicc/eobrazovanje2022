package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoursePerformance {
    //izvodjenje predmeta

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int schoolYear;

    @ManyToMany
    private List<CourseTeacher> courseTeachers;

    @ManyToOne
    private Course course;
}
