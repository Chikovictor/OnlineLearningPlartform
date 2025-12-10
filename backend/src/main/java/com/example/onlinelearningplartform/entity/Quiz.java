package com.example.OnlineLearningPlartform.entity;

import javax.persistence.*;

// @Entity: Maps to 'Quiz' table.
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // id: Unique ID.

    private String question;  // question: Quiz question, e.g., "When did Kenya gain independence?".

    private String correctAnswer;  // correctAnswer: The right answer, e.g., "1963". (Simple, no options for brevity)

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;  // course: Parent course.

    public Quiz() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}