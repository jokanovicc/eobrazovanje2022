package com.ftn.eobrazovanje.model;

import com.ftn.eobrazovanje.model.roles.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer schoolYear;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Course course;




}
