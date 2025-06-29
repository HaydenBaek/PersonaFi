import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api/quiz';

export const getQuizQuestions = async () => {

    try {
        const response = await axios.get(API_BASE_URL);
        return response.data;
    } catch (error) {
        console.error(error);
        throw error;
    }
}