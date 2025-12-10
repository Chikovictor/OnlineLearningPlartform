import React, { useState, useEffect } from 'react';  // Hooks: useState for state, useEffect for side effects.
import axios from 'axios';  // Axios: For API calls.
import CourseList from './components/CourseList';  // Import components.
import LessonView from './components/LessonView';
import QuizForm from './components/QuizForm';

// App: Functional component - a function returning JSX (HTML-like syntax in JS).
function App() {
    // useState: Hook to add state to functional components. Returns [value, setter].
    const [courses, setCourses] = useState([]);  // courses: Array of courses from backend.
    const [selectedCourse, setSelectedCourse] = useState(null);  // selectedCourse: For viewing lessons/quizzes.
    const [loading, setLoading] = useState(true);  // loading: For loading state.
    const [error, setError] = useState(null);  // error: For error state.

    // useEffect: Hook for side effects like API calls. Runs after render.
    // Empty [] means run once on mount.
    useEffect(() => {
        // Async function inside: Fetches data asynchronously.
        const fetchCourses = async () => {
            try {
                // axios.get: Makes GET request to backend API.
                const response = await axios.get('http://localhost:8080/api/courses');
                setCourses(response.data);  // Update state with data.
                setLoading(false);  // End loading.
            } catch (err) {
                setError('Failed to fetch courses');  // Handle error.
                setLoading(false);
            }
        };
        fetchCourses();
    }, []);  // Dependency array: Empty, so runs once.

    // Function to handle enrollment (dummy).
    const handleEnroll = async (courseId) => {
        try {
            await axios.post('http://localhost:8080/api/courses/enroll', { courseId });
            alert('Enrolled successfully!');  // alert: Browser popup.
        } catch (err) {
            alert('Enrollment failed');
        }
    };

    // JSX: Returns UI. Conditional rendering for loading/error.
    if (loading) return <div className="container">Loading...</div>;  // className: Like HTML class for Bootstrap.
    if (error) return <div className="container">Error: {error}</div>;

    return (
        <div className="container mt-5">  // Bootstrap classes: container for layout, mt-5 for margin.
            <h1>Online Learning Platform - Kenyan Context</h1>  // h1: Heading.
            <p>Welcome to courses with Kenyan focus, e.g., History of Kenya.</p>
            <CourseList
                courses={courses}  // Props: Pass data to child component (prop drilling).
                onEnroll={handleEnroll}
                onSelect={setSelectedCourse}  // onSelect: Callback prop.
            />
            {selectedCourse && (  // Conditional: If selectedCourse, show lessons/quizzes.
                <>
                    <LessonView courseId={selectedCourse} />  // <> </>: Fragment for grouping without extra div.
                    <QuizForm courseId={selectedCourse} />
                </>
            )}
        </div>
    );
}

export default App;  // export: Makes it importable.