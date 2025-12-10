import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';  // Imports Bootstrap CSS for styling.

// React: Library for building user interfaces using components.
// ReactDOM: Provides methods to render React components to the DOM (Document Object Model - browser's HTML structure).

// createRoot: Creates a root for rendering (new in React 18).
const root = ReactDOM.createRoot(document.getElementById('root'));
// render: Renders the <App /> component into the HTML element with id 'root'.
root.render(
    <React.StrictMode>  // StrictMode: Helps identify potential problems in development.
        <App />
    </React.StrictMode>
);

// Exam concept: Virtual DOM
// React uses a Virtual DOM - a lightweight in-memory representation of the real DOM.
// When state changes, React updates the Virtual DOM first, computes the diff (reconciliation), and applies minimal changes to the real DOM.
// This improves performance by avoiding direct DOM manipulations. See useState/useEffect in components for triggers.