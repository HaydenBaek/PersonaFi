import axios from 'axios'

const API_BASE_URL = 'http://localhost:8080/api/quiz';


//Makes a GET request to the backend
//Returns the list of questions as JSON data
//Logs and throws an error if the request fails
export const getQuizQuestions = async () => {

    //using try just to catch errors and log errors if anything is broken
    try {
        //sending GET request to my backend URL -> to fetch quiz questions
        const response = await axios.get(API_BASE_URL);
        
        //gives me the actual JSON body returned from the backend
        return response.data;
    } catch (error) {
        console.error(error);
        throw error;
    }
}

//Makes a POST request to the backend
//Sends the users' answers in the "request" body
//Returns the personality result from the backend
export const submitQuizAnswers = async (userAnswers) => {
    try {
        //sending the data to the backend
        console.log('Sending this to backend:', userAnswers);
        const response = await axios.post('http://localhost:8080/api/quiz/submit', userAnswers);
        
        //getting back the response -> personality type of the user
        return response.data;
    } catch (error) {
        console.error(error)
        throw error
    }
}