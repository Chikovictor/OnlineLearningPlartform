package com.example.onlinelearningplatform.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Import Jackson annotations
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// @Entity: Marks this class as a JPA entity, meaning it maps to a database table.
@Entity
public class Course {

    // @Id: Marks this field as the primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // id: Unique identifier for the course.

    private String title;  // title: Name of the course, e.g., "Kenyan History".

    private String description;  // description: Brief info about the course.


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("course-lessons")  // Add this line
    private List<Lesson> lessons = new ArrayList<>();

    // Add @JsonManagedReference for quizzes too
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("course-quizzes")  // Add this line
    private List<Quiz> quizzes = new ArrayList<>();


    // Default constructor: Required by JPA for entity instantiation.
    public Course() {}

    // Getters and Setters: Methods to access/modify private fields.
    // Getter: Returns the value. Setter: Sets the value.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<Lesson> getLessons() { return lessons; }
    public void setLessons(List<Lesson> lessons) { this.lessons = lessons; }
    public List<Quiz> getQuizzes() { return quizzes; }
    public void setQuizzes(List<Quiz> quizzes) { this.quizzes = quizzes; }

    // Helper method to add a lesson
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
        lesson.setCourse(this);
    }
    
    // Helper method to add a quiz
    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        quiz.setCourse(this);
    }
}