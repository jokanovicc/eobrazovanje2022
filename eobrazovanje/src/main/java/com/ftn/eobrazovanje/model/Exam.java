package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Exam {
    //ISPIT

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long preExamDutyPoints;

    private Long finalExamPoints;

    @ManyToOne
    private Attending attending;

    private Integer grade;

    private LocalDate date;

    private String classroom;


}
