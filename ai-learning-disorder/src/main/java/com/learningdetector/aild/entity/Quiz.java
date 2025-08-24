package com.learningdetector.aild.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime conductedOn;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuizAttempt> attempts;

    @PrePersist
    protected void onCreate() {
        if (conductedOn == null) {
            conductedOn = LocalDateTime.now();  // ✅ fallback if teacher doesn’t set it
        }
    }
}
