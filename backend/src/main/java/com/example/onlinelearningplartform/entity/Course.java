package com.example.OnlineLearningPlartform.entity;

import javax.persistence.*;
import java.util.List;

// @Entity: Marks this class as a JPA entity, meaning it maps to a database table.
// Table name defaults to class name 'Course'.
@Entity
public class Course {

    // @Id: Marks this field as the primary key.
    // @GeneratedValue: Auto-generates the ID value using database strategy (e.g., auto-increment).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // id: Unique identifier for the course.

    private String title;  // title: Name of the course, e.g., "Kenyan History".

    private String description;  // description: Brief info about the course.

    // @OneToMany: Defines a one-to-many relationship (one course has many lessons).
    // mappedBy: The field in Lesson that owns the relationship (foreign key in Lesson table).
    // cascade: Operations like save/delete on Course propagate to Lessons.
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons;  // lessons: List of lessons belonging to this course.

    // Similar to above for quizzes.
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Quiz> quizzes;  // quizzes: List of quizzes for this course.

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

    // Exam concept: Dependency Injection (DI)
    // DI is a design pattern where dependencies (e.g., services) are provided to a class instead of created inside it.
    // In Spring, @Autowired injects beans automatically. Here, repositories are injected into controllers (see controllers).
    // This promotes loose coupling and testability. For example, CourseRepository is injected without 'new'.
}