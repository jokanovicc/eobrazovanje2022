package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Teacher {


    @Id
    private Long id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    public Teacher(Long id) {
        this.id = id;
    }
}
