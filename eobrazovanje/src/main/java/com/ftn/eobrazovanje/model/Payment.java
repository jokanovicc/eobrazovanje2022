package com.ftn.eobrazovanje.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    private Double amount;

    private String text;

    private String accountNumber;

    private LocalDate paymentDate;

    public Payment(Student student, Double amount, String text, String accountNumber, LocalDate paymentDate) {
        this.student = student;
        this.amount = amount;
        this.text = text;
        this.accountNumber = accountNumber;
        this.paymentDate = paymentDate;
    }
}
