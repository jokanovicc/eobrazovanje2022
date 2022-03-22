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
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    private Student student;

    @ManyToOne
    private PreExamDuty preExamDuty;



}
