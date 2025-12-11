package com.example.onlinelearningplatform.controller;

import com.example.onlinelearningplatform.entity.Course;
import com.example.onlinelearningplatform.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

// @RestController: Marks this class as a REST controller; methods return JSON directly.
// @RequestMapping: Sets the base URL path for all methods in this class.

@CrossOrigin(origins = "http://localhost:3000")  // Allows React to fetch data
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    // @Autowired: Spring's Dependency Injection - injects the CourseRepository bean automatically.
    // No need to create it with 'new'; Spring manages the lifecycle.
    @Autowired
    private CourseRepository courseRepository;

    // @GetMapping: Handles GET requests to fetch all courses.
    // Returns List<Course> as JSON.
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();  // findAll(): JPA method to retrieve all records.
    }

    // @PostMapping: Handles POST to create a new course.
    // @RequestBody: Binds JSON body to Course object.
    // ResponseEntity: Wraps response with HTTP status.
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course saved = courseRepository.save(course);  // save(): Persists the entity to DB.
        return new ResponseEntity<>(saved, HttpStatus.CREATED);  // 201 Created status.
    }

    // @GetMapping with path variable: Fetches one course by ID.
    // @PathVariable: Binds URL param to method arg.
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseRepository.findById(id);  // findById(): Returns Optional to handle null.
        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);  // 200 OK.
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 Not Found - proper error handling.
        }
    }

    // Similar for update (PUT) and delete (DELETE).
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Optional<Course> existing = courseRepository.findById(id);
        if (existing.isPresent()) {
            updatedCourse.setId(id);  // Preserve ID.
            Course saved = courseRepository.save(updatedCourse);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseRepository.existsById(id)) {  // existsById(): Checks if record exists.
            courseRepository.deleteById(id);  // deleteById(): Removes record.
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content.
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Specific endpoint: /api/courses/enroll - Dummy enrollment (e.g., for student).
    // In real app, would link to user; here, just returns success message.
    @PostMapping("/enroll")
    public ResponseEntity<String> enrollInCourse(@RequestBody EnrollmentRequest request) {  // Custom request class below.
        // Assume success; in real, check if course exists.
        return new ResponseEntity<>("Enrolled in course " + request.getCourseId(), HttpStatus.OK);
    }

    // Inner class for request body.
    public static class EnrollmentRequest {
        private Long courseId;
        public Long getCourseId() { return courseId; }
        public void setCourseId(Long courseId) { this.courseId = courseId; }
    }
}