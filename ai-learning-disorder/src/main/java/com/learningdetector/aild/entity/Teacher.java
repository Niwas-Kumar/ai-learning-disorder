package com.learningdetector.aild.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String name;
    private String email;

}


