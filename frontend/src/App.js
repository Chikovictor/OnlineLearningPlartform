import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CourseList from './components/CourseList';
import LessonView from './components/LessonView';
import QuizForm from './components/QuizForm';
import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
    const [courses, setCourses] = useState([]);
    const [selectedCourse, setSelectedCourse] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchCourses = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/courses');
                setCourses(response.data || []);
                setLoading(false);
            } catch (err) {
                console.error("Error fetching courses:", err);
                setError('Failed to load courses. Is backend running on port 8080?');
                setLoading(false);
            }
        };
        fetchCourses();
    }, []);

    const handleEnroll = async (courseId) => {
        try {
            await axios.post('http://localhost:8080/api/courses/enroll', { courseId });
            alert('Enrolled successfully!');
        } catch (err) {
            alert('Enrollment failed');
        }
    };

    if (loading) return <div className="container mt-5"><h2>Loading courses...</h2></div>;
    if (error) return <div className="container mt-5 text-danger"><h2>Error: {error}</h2><p>Check if backend is running.</p></div>;

    return (
        <div className="container mt-5">
            <h1>Online Learning Platform - Kenyan Context</h1>
            <p>Welcome! Explore courses on Kenyan history and culture.</p>

            {courses.length === 0 ? (
                <div className="alert alert-info">
                    <h4>No courses found</h4>
                    <p>Add a course using Postman (teacher mode), then refresh.</p>
                </div>
            ) : (
                <CourseList
                    courses={courses}
                    onEnroll={handleEnroll}
                    onSelect={setSelectedCourse}
                />
            )}

            {selectedCourse && (
                <>
                    <hr />
                    <LessonView courseId={selectedCourse} />
                    <QuizForm courseId={selectedCourse} />
                </>
            )}
        </div>
    );
}

export default App;