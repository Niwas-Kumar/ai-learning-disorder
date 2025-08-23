package com.learningdetector.aild.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quizattempts")
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AttemptId;

    private int correct;
    private int incorrect;
    private float avgLatencySec;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


}
