package com.ftn.eobrazovanje.model;


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
    private Long id;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private TeacherRole teacherRole;

    public CourseTeacher(Long id) {
        this.id = id;
    }

    public CourseTeacher(Teacher teacher, TeacherRole teacherRole) {
        this.teacher = teacher;
        this.teacherRole = teacherRole;
    }
}
