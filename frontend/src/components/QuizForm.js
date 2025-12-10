import React, { useState, useEffect } from 'react';
import axios from 'axios';

const QuizForm = ({ courseId }) => {
    const [quizzes, setQuizzes] = useState([]);
    const [answers, setAnswers] = useState({});  // answers: Object for user inputs.
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [result, setResult] = useState(null);

    useEffect(() => {
        const fetchQuizzes = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/courses/${courseId}`);
                setQuizzes(response.data.quizzes || []);
                setLoading(false);
            } catch (err) {
                setError('Failed to fetch quizzes');
                setLoading(false);
            }
        };
        fetchQuizzes();
    }, [courseId]);

    // Handle input change.
    const handleChange = (quizId, value) => {
        setAnswers(prev => ({ ...prev, [quizId]: value }));  // Spread operator: Updates object.
    };

    // Submit all answers (simple, one by one).
    const handleSubmit = async () => {
        for (const [quizId, userAnswer] of Object.entries(answers)) {  // Loop over answers.
            try {
                const response = await axios.post('http://localhost:8080/api/quizzes/submit', { quizId, userAnswer });
                setResult(response.data);  // Show result.
            } catch (err) {
                setResult('Submission failed');
            }
        }
    };

    if (loading) return <div>Loading quizzes...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <h2>Quizzes</h2>
            {quizzes.map(quiz => (
                <div key={quiz.id}>
                    <p>{quiz.question}</p>  // Kenyan context, e.g., question about Kenya.
                    <input
                        type="text"
                        onChange={(e) => handleChange(quiz.id, e.target.value)}  // e.target.value: Input value.
                        className="form-control"
                    />
                </div>
            ))}
            <button className="btn btn-success" onClick={handleSubmit}>Submit Quiz</button>
            {result && <p>Result: {result}</p>}
        </div>
    );
};

export default QuizForm;