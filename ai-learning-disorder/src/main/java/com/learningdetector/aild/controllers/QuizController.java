package com.learningdetector.aild.controllers;

import com.learningdetector.aild.dto.QuizDTO;
import com.learningdetector.aild.entity.Quiz;
import com.learningdetector.aild.repositories.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizRepository quizRepo;

    public QuizController(QuizRepository quizRepo) {
        this.quizRepo = quizRepo;
    }

    // ✅ Create quiz
    @PostMapping
    public ResponseEntity<QuizDTO> createQuiz(@RequestBody QuizDTO dto) {
        Quiz quiz = Quiz.builder()
                .title(dto.getTitle())
                .conductedOn(dto.getConductedOn()) // may be null → handled by @PrePersist
                .build();

        Quiz saved = quizRepo.save(quiz);

        return ResponseEntity.ok(new QuizDTO(
                saved.getQuizId(),
                saved.getTitle(),
                saved.getConductedOn()
        ));
    }

    // ✅ List all quizzes
    @GetMapping
    public ResponseEntity<List<QuizDTO>> listQuizzes() {
        List<QuizDTO> quizzes = quizRepo.findAll().stream()
                .map(q -> new QuizDTO(q.getQuizId(), q.getTitle(), q.getConductedOn()))
                .toList();

        return ResponseEntity.ok(quizzes);
    }
}
