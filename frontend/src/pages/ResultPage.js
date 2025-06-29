import React from 'react';
import { useLocation } from 'react-router-dom';


function ResultPage() {
    //its a react router hook to get info about the current page navigation
    const location = useLocation();
    //storing the result sent from QuizPage.js
    const result = location.state?.result;


    return (
        <div>
            <h1>This is the Result Page</h1>
            <h1>Your Personality Type:</h1>
            <p>{result}</p>

        </div>
    );
}

export default ResultPage;
