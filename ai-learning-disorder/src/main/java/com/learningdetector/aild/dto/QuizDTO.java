package com.learningdetector.aild.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizDTO {
    private Long quizId;
    private String title;
    private LocalDateTime conductedOn; // optional, can be null
}
