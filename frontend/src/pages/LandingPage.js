import React from 'react';
import { useNavigate } from 'react-router-dom';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';

function LandingPage() {
    const navigate = useNavigate();

    const handleStartQuiz = () => {
        navigate('/quiz');
    };

    return (
        <Box
            sx={{
                height: '100vh',
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                backgroundColor: 'background.default',
                textAlign: 'center',
                padding: 2,
            }}
        >
            <Typography variant="h3" color="primary" gutterBottom>
                Welcome to PersonaFi
            </Typography>

            <Typography variant="subtitle1" color="text.primary" gutterBottom>
                Discover your financial personality
            </Typography>

            <Button
                variant="contained"
                color="primary"
                size="large"
                onClick={handleStartQuiz}
                sx={{ marginTop: 2 }}
            >
                Start Quiz
            </Button>
        </Box>
    );
}

export default LandingPage;
