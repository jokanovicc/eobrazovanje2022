package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {

    @Id
    private Long id;

    private String indexNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Column(unique = true)
    private String referenceNumber;

    private String passwordToken;

    private boolean firstLogin;

    private boolean completedSVForm;


    public Student(Long id, String indexNumber, User user, String referenceNumber) {
        this.id = id;
        this.indexNumber = indexNumber;
        this.user = user;
        this.referenceNumber = referenceNumber;
    }
}
