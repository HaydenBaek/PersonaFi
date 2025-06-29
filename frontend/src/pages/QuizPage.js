import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getQuizQuestions, submitQuizAnswers } from '../services/quizService'

//QuizPage component, it shows when the user visits /quiz
function QuizPage() {

    //holds the questions fetched from the backend
    const [questions, setQuestions] = useState([]);
    //trakcs the answers the user selects
    const [userAnswers, setUserAnswers] = useState([]);
    //lets you navigate to different routes/pages
    const navigate = useNavigate();


    useEffect(() => {
        async function fetchQuestions() {
            try {
                //makes the GET API call to the backend
                const data = await getQuizQuestions();
                console.log('Questions:', data);

                setQuestions(data);
            } catch (error) {
                console.error('Failed to load quetsions', error)
            }
        }

        fetchQuestions();
    }, []); //only runs once when the page first loads


    //Runs every time a user picks an optoin
    const handleAnswerSelect = (questionIndex, selectedOption) => {

        //makes a copy of current answers
        const updatedAnswers = [...userAnswers];

        //upate answer for that uestion
        updatedAnswers[questionIndex] = selectedOption;

        //save updated answers back to state
        setUserAnswers(updatedAnswers);
    };


    const handleSubmit = async () => {
        try {

            //sends POST requset tot he backend
            const result = await submitQuizAnswers(userAnswers);
            console.log("result form the backend", result)

            //goes to /result page and pass the result
            navigate('/result', { state: { result } });
        } catch (error) {
            console.error("submitting the quiz error", error);
        }
    }



    return (
        <div>
            <h1>Quiz Page</h1>
            {questions.map((question, index) => (
                <div key={index}>
                    <h3>{question.questionText}</h3>
                    <ul>
                        {question.options.map((option, idx) => (
                            <div key={idx}>
                                <label>
                                    <input
                                        type="radio"
                                        name={`question-${index}`} // grouping each question's options
                                        value={option}
                                        checked={userAnswers[index] === option}
                                        onChange={() => handleAnswerSelect(index, option)}
                                    />
                                    {option}
                                </label>
                            </div>
                        ))}

                    </ul>

                </div>
            ))}
            <button onClick={handleSubmit}>Submit Quiz</button>
        </div>
    );


}

export default QuizPage;
