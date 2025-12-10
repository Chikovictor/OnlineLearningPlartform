import React from 'react';  // React: Required for JSX.

// CourseList: Functional component with props.
const CourseList = ({ courses, onEnroll, onSelect }) => {  // Destructuring props.
    return (
        <ul className="list-group">  // Bootstrap list-group for styled list.
            {courses.map(course => (  // map: Loops over array, returns JSX for each.
                <li key={course.id} className="list-group-item">  // key: Unique for React's reconciliation.
                    {course.title} - {course.description}
                    <button className="btn btn-primary mx-2" onClick={() => onEnroll(course.id)}>Enroll</button>  // onClick: Event handler.
                    <button className="btn btn-secondary" onClick={() => onSelect(course.id)}>View</button>
                </li>
            ))}
        </ul>
    );
};

export default CourseList;