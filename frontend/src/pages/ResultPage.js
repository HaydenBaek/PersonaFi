import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Box, Typography, Button, Paper } from '@mui/material';



function ResultPage() {
    //its a react router hook to get info about the current page navigation
    const location = useLocation();
    //storing the result sent from QuizPage.js
    const result = location.state?.result;

    const navigate = useNavigate();

    //function to let the user retake the quiz
    const handleRetakeQuiz = () => {
        navigate('/quiz');
    }




    return (
        <Box
            sx={{
                height: '100vh',
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                backgroundColor: 'background.default',
                padding: 3,
                textAlign: 'center',
            }}
        >
            <Paper elevation={4} sx={{ padding: 4, maxWidth: '600px', width: '100%' }}>
                <Typography variant="h4" color="primary" gutterBottom>
                    Your Personality Result
                </Typography>

                <Typography variant="h5" color="secondary" gutterBottom>
                    {result.type}
                </Typography>

                <Typography variant="h6" color="text.primary" gutterBottom>
                    Description:
                </Typography>
                <Typography variant="body1" color="text.secondary" gutterBottom>
                    {result.description}
                </Typography>

                <Typography variant="h6" color="text.primary" gutterBottom>
                    Tips:
                </Typography>
                <Typography variant="body1" color="text.secondary" gutterBottom>
                    {result.tips}
                </Typography>


                <Button
                    variant="contained"
                    color="secondary"
                    onClick={handleRetakeQuiz}
                    sx={{ marginTop: 2 }}
                >
                    Retake Quiz
                </Button>
            </Paper>
        </Box>
    );
}

export default ResultPage;
