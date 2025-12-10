Project Report: Online Learning Platform
Overview
This project is a simple online learning platform with backend in Spring Boot and frontend in React. It allows students to enroll in courses, view lessons, and complete quizzes. Teachers can upload content via APIs (e.g., POST to create courses/lessons/quizzes). Kenyan context is integrated with sample data on Kenyan History (e.g., lessons on Mount Kenya, quizzes on independence).
Backend

Entities: Course, Lesson, Quiz with OneToMany relationships.
Endpoints: CRUD for all, plus enroll and submit.
DB: H2 for dev, MySQL script provided.
Error Handling: HTTP codes like 404.
Exam Concept: Dependency Injection in controllers (explained in code).

Frontend

Components: CourseList, LessonView, QuizForm using hooks.
Integration: Axios for API, state management with useState/useEffect.
UI: Bootstrap for responsive, loading/error states.
Exam Concept: Virtual DOM explained in index.js.

Integration

CORS enabled.
Kenyan Context: UI shows Kenyan-focused content.

Challenges
Kept simple; no auth/users.