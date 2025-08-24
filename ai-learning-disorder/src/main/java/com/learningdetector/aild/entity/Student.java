package com.learningdetector.aild.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;


    private String name;
    private String grade;
    private String section;

    @Column(unique = true, nullable = false)
    private String guardianContact;

    // relationships
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<QuizAttempt> quizAttempts;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<HandwritingSample> handwritingSamples;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Prediction> predictions;


}
