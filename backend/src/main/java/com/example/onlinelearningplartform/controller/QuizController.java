package com.example.OnlineLearningPlartform.controller;

import com.example.onlinelearning.entity.Quiz;
import com.example.onlinelearning.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz saved = quizRepository.save(quiz);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz) {
        Optional<Quiz> existing = quizRepository.findById(id);
        if (existing.isPresent()) {
            updatedQuiz.setId(id);
            Quiz saved = quizRepository.save(updatedQuiz);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Specific endpoint: /api/quiz/submit - Submits answer and checks if correct.
    // In real app, would save score; here, simple check.
    @PostMapping("/submit")
    public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmission submission) {
        Optional<Quiz> quiz = quizRepository.findById(submission.getQuizId());
        if (quiz.isPresent()) {
            if (quiz.get().getCorrectAnswer().equals(submission.getUserAnswer())) {
                return new ResponseEntity<>("Correct!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Incorrect. Correct answer: " + quiz.get().getCorrectAnswer(), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<>("Quiz not found", HttpStatus.NOT_FOUND);
        }
    }

    // Inner class for submission body.
    public static class QuizSubmission {
        private Long quizId;
        private String userAnswer;
        public Long getQuizId() { return quizId; }
        public void setQuizId(Long quizId) { this.quizId = quizId; }
        public String getUserAnswer() { return userAnswer; }
        public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }
    }
}