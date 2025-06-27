import React from 'react';
import { useNavigate } from 'react-router-dom';

function Landingpage() {

    const navigate = useNavigate();

    const handleStartQuiz = () => {
        navigate('/quiz')
    }

    return (
        <div>
            <h1>Welcome</h1>
            <button onClick={handleStartQuiz}>Start Quiz</button>
        </div>
    );
}

export default Landingpage;