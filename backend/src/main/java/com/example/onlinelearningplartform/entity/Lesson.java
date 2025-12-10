package com.example.OnlineLearningPlartform.entity;

import javax.persistence.*;

// @Entity: Maps to 'Lesson' table.
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // id: Unique ID.

    private String title;  // title: Lesson name, e.g., "Mount Kenya Overview".

    private String content;  // content: Lesson text, with Kenyan context e.g., "Mount Kenya is the highest mountain in Kenya...".

    // @ManyToOne: Many lessons belong to one course.
    // @JoinColumn: Defines the foreign key column 'course_id' in Lesson table.
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;  // course: The parent course.

    public Lesson() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}