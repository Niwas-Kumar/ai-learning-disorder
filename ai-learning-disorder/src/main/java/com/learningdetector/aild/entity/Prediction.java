package com.learningdetector.aild.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long predictionId;

    private String riskLevel;
    private String explanation;
    private Date predictedOn;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
