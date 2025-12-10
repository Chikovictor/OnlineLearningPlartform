package com.example.OnlineLearningPlartform.controller;

import com.example.onlinelearning.entity.Lesson;
import com.example.onlinelearning.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson saved = lessonRepository.save(lesson);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        return lesson.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        Optional<Lesson> existing = lessonRepository.findById(id);
        if (existing.isPresent()) {
            updatedLesson.setId(id);
            Lesson saved = lessonRepository.save(updatedLesson);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}