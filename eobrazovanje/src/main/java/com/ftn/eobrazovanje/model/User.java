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
    private String lastname;
    private String jmbg;
    private String address;
    private String username;
    private String password;
//    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String gender;

    public User(Long id, String name,String lastname, String jmbg, String address, String username, UserRole role, String gender, String password, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.address = address;
        this.username = username;
        this.role = role;
        this.gender = gender;
        this.password = password;
        this.email = email;
    }

    public User(Long id, String name,String lastname, String jmbg, String address, String username, String gender, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.jmbg = jmbg;
        this.address = address;
        this.username = username;
        this.gender = gender;
        this.email = email;
    }
}
