import React, { useState, useEffect } from 'react';
import { getQuizQuestions } from '../services/quizService'

function QuizPage() {

    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        async function fetchQuestions() {
            try {
                const data = await getQuizQuestions();
                console.log('Questions:', data);

                setQuestions(data);
            } catch (error) {
                console.error('Failed to load quetsions', error)
            }
        }

        fetchQuestions();
    }, []); //only runs once when the page first loads

    return (
    <div>
        <h1>Quiz Page</h1>
        {questions.map((question, index) => (
            <div key={index}>
                <h3>{question.questionText}</h3>
                <ul>
                    {question.options.map((option, idx) => (
                        <li key={idx}>{option}</li>
                    ))}
                </ul>
            </div>
        ))}
    </div>
);


}

export default QuizPage;
