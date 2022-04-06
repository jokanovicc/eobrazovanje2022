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
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<DocumentFile> documentFiles = new ArrayList<>();

    private String name;

    @ManyToOne
    private DocumentType type;



}
