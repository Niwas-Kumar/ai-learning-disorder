package com.learningdetector.aild.controllers;

import com.learningdetector.aild.dto.QuestionDTO;
import com.learningdetector.aild.entity.Question;
import com.learningdetector.aild.entity.Quiz;
import com.learningdetector.aild.repositories.QuestionRepository;
import com.learningdetector.aild.repositories.QuizRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class QuestionController {

    private final QuizRepository quizRepo;
    private final QuestionRepository questionRepo;

    public QuestionController(QuizRepository quizRepo, QuestionRepository questionRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
    }

    // ✅ Add multiple questions to a quiz
    @PostMapping("/{quizId}/questions")
    public ResponseEntity<?> addQuestions(
            @PathVariable Long quizId,
            @Valid @RequestBody List<QuestionDTO> request,
            BindingResult result
    ) {
        // handle validation errors
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Validation failed",
                    "errors", errors
            ));
        }

        Optional<Quiz> quizOpt = quizRepo.findById(quizId);
        if (quizOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
        }
        Quiz quiz = quizOpt.get();

        List<Question> toSave = request.stream().map(dto -> {
            Question q = new Question();
            q.setText(dto.getText());
            q.setOptionA(dto.getOptionA());
            q.setOptionB(dto.getOptionB());
            q.setOptionC(dto.getOptionC());
            q.setOptionD(dto.getOptionD());
            q.setCorrectAnswer(dto.getCorrectAnswer().trim().toUpperCase(Locale.ROOT));
            q.setQuiz(quiz);
            return q;
        }).collect(Collectors.toList());

        questionRepo.saveAll(toSave);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Added " + toSave.size() + " questions to quiz " + quizId
        ));
    }

    // ✅ List all questions for a quiz
    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<QuestionDTO>> listQuestions(@PathVariable Long quizId) {
        if (!quizRepo.existsById(quizId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<QuestionDTO> dtos = questionRepo.findByQuizQuizId(quizId).stream()
                .map(q -> new QuestionDTO(
                        q.getQuestionId(),
                        q.getText(),
                        q.getOptionA(),
                        q.getOptionB(),
                        q.getOptionC(),
                        q.getOptionD(),
                        q.getCorrectAnswer()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
