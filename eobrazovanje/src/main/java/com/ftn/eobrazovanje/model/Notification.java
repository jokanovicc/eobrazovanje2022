package com.ftn.eobrazovanje.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_user_id")
    private Teacher teacher;

    private String message;

    public Notification(Performance performance, Teacher teacher, String message) {
        this.performance = performance;
        this.teacher = teacher;
        this.message = message;
    }
}
