import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';

// React: Library for building user interfaces using components.
// ReactDOM: Provides methods to render React components to the DOM.

// createRoot: Creates a root for rendering.
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    // StrictMode: Helps identify potential problems in development.
    <React.StrictMode>
        <App />
    </React.StrictMode>
);

// Exam concept: Virtual DOM
// React uses a Virtual DOM - a lightweight in-memory representation of the real DOM.
