import React from 'react';  // React: Required for JSX.

// CourseList: Functional component with props.
const CourseList = ({ courses, onEnroll, onSelect }) => {  // Destructuring props.
    return (
        // Bootstrap list-group for styled list.
        <ul className="list-group">
            {courses.map(course => (  // map: Loops over array, returns JSX for each.
                <li key={course.id} className="list-group-item">
                    {course.title} - {course.description}
                    <button className="btn btn-primary mx-2" onClick={() => onEnroll(course.id)}>Enroll</button>
                    <button className="btn btn-secondary" onClick={() => onSelect(course.id)}>View</button>
                </li>
            ))}
        </ul>
    );
};

export default CourseList;