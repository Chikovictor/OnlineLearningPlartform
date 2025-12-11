package com.example.onlinelearningplatform.repository;

import com.example.onlinelearningplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: Marks this as a Spring repository bean, enabling exception translation.
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Extends JpaRepository: Provides methods like findAll(), save(), deleteById(), findById().
    // <Course, Long>: Entity type and ID type.
    // No need for custom methods here; CRUD is built-in.
}