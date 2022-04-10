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
public class Performance {
    //izvodjenje predmeta

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int schoolYear;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "performance_teacher_relationship",
            joinColumns = { @JoinColumn(name = "performance_id") },
            inverseJoinColumns = { @JoinColumn(name = "teacher_id") })
    private List<CourseTeacher> courseTeachers;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Performance(Long id) {
        this.id = id;
    }

    public Performance(int schoolYear, Course course) {
        this.schoolYear = schoolYear;
        this.course = course;
    }

    public Performance(int schoolYear, List<CourseTeacher> courseTeachers, Course course) {
        this.schoolYear = schoolYear;
        this.courseTeachers = courseTeachers;
        this.course = course;
    }
}
