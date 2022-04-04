package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudyProgram {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

    private Integer studyLenght;

    @ManyToOne
    private StudyType studyType;

}
