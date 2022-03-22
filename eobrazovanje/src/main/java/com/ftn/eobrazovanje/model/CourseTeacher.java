package com.ftn.eobrazovanje.model;


import com.ftn.eobrazovanje.model.roles.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseTeacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private TeacherRole teacherRole;





}
