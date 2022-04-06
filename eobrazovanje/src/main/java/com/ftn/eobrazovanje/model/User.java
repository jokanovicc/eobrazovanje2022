package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String jmbg;
    private String address;
    private String username;
    private String password;

    @ManyToOne
    private Role role;

    private String gender;

    public User(Long id, String name, String jmbg, String address, String username, Role role, String gender) {
        this.id = id;
        this.name = name;
        this.jmbg = jmbg;
        this.address = address;
        this.username = username;
        this.role = role;
        this.gender = gender;
    }
}
