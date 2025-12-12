import React, { useState, useEffect } from 'react';
import axios from 'axios';

const LessonView = ({ courseId }) => {
    const [lessons, setLessons] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchLessons = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/courses/${courseId}`);
                setLessons(response.data.lessons || []);
                setLoading(false);
            } catch (err) {
                setError('Failed to fetch lessons');
                setLoading(false);
            }
        };
        fetchLessons();
    }, [courseId]);

    if (loading) return <div>Loading lessons...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h2>Lessons</h2>
            {lessons.map(lesson => (
                <div key={lesson.id}>
                    <h3>{lesson.title}</h3>

                    {/* Kenyan context here, e.g., content about Kenya. */}
                    <p>{lesson.content}</p>
                </div>
            ))}
        </div>
    );
};

export default LessonView;
