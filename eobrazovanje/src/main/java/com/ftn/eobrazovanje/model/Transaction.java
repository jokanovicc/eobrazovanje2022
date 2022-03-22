package com.ftn.eobrazovanje.model;

import com.ftn.eobrazovanje.model.roles.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Student student;

    private Double amount;

    private String purpose;

    private String referenceNumber;


}
