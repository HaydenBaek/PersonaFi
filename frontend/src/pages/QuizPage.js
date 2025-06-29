import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getQuizQuestions, submitQuizAnswers } from '../services/quizService'
import { Button, Typography, Box, Paper, Radio, RadioGroup, FormControlLabel } from '@mui/material';


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
                //filling up the userAnswers with undefined for validation
                setUserAnswers(new Array(data.length).fill(undefined));

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

        //validating, but this will never be called, since the SUBMIT button will be disabled unless the user answers all questions.
        //putting it here just in case if handleSubmit gets triggered somehow
        if (!isQuizComplete()) {
            alert('Please answer all questions before submitting.');
            return;
        }

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

    //validation function to check if the userAnswers array's length matches the number of quetsions
    const isQuizComplete = () => {
        return userAnswers.length === questions.length && userAnswers.every(answer => answer !== undefined);
    }



return (
        <Box sx={{ maxWidth: '800px', margin: '0 auto', padding: 4 }}>
            <Typography variant="h4" color="primary" gutterBottom>
                Quiz Page
            </Typography>

            {questions.map((question, index) => (
                <Paper key={index} elevation={3} sx={{ padding: 3, marginBottom: 3 }}>
                    <Typography variant="h6" gutterBottom>
                        {question.questionText}
                    </Typography>

                    <RadioGroup
                        name={`question-${index}`}
                        value={userAnswers[index] || ''}
                        onChange={(e) => handleAnswerSelect(index, e.target.value)}
                    >
                        {question.options.map((option, idx) => (
                            <FormControlLabel
                                key={idx}
                                value={option}
                                control={<Radio color="secondary" />}
                                label={option}
                            />
                        ))}
                    </RadioGroup>
                </Paper>
            ))}

            <Button
                variant="contained"
                color="primary"
                onClick={handleSubmit}
                disabled={!isQuizComplete()}
            >
                Submit Quiz
            </Button>
        </Box>
    );


}

export default QuizPage;
