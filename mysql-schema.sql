-- MySQL script for production database schema.
-- Creates database and tables.

CREATE DATABASE IF NOT EXISTS online_learning;  -- Creates DB if not exists.

USE online_learning;  -- Switches to DB.

-- Course table.
CREATE TABLE course (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- AUTO_INCREMENT: Auto-generates ID.
                        title VARCHAR(255),  -- VARCHAR: String type.
                        description VARCHAR(255)
);

-- Lesson table with foreign key.
CREATE TABLE lesson (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255),
                        content TEXT,  -- TEXT: For longer content.
                        course_id BIGINT,
                        FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE  -- Foreign key: Links to course, cascade delete.
);

-- Quiz table similar.
CREATE TABLE quiz (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      question VARCHAR(255),
                      correct_answer VARCHAR(255),
                      course_id BIGINT,
                      FOREIGN KEY (course_id) REFERENCES course(id) ON DELETE CASCADE
);