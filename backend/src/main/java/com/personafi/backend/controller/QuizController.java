package com.personafi.backend.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personafi.backend.model.PersonalityType;
//importing my Question Model
import com.personafi.backend.model.Question;
import com.personafi.backend.service.QuizService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/quiz")
public class QuizController {
        @Autowired
        private QuizService quizService;

        @GetMapping
        public List<Question> getQuiz() {
                Question q1 = new Question(
                                "When you get paid, what's the first thing you do?",
                                Arrays.asList("Transfer to savings", "Check investments", "Buy something for fun",
                                                "Ignore it"));

                Question q2 = new Question(
                                "How do you feel about budgeting?",
                                Arrays.asList("Love it", "Hate it", "Never tried", "Do it sometimes"));

                Question q3 = new Question(
                                "What's your approach to investing?",
                                Arrays.asList("High-risk, high-reward", "Long-term, steady growth", "I avoid investing",
                                                "Haven’t thought about it"));

                Question q4 = new Question(
                                "When shopping, you mostly...",
                                Arrays.asList("Compare prices and look for deals", "Buy impulsively",
                                                "Stick to a pre-planned list",
                                                "Buy based on mood"));

                Question q5 = new Question(
                                "What’s your biggest financial priority?",
                                Arrays.asList("Growing wealth", "Stability and safety", "Enjoying life in the moment",
                                                "Saving for future goals"));

                Question q6 = new Question(
                                "How do you feel about debt?",
                                Arrays.asList("Avoid it at all costs", "Use it strategically", "It’s just part of life",
                                                "Don’t really think about it"));

                Question q7 = new Question(
                                "How often do you check your bank account?",
                                Arrays.asList("Daily", "Weekly", "Monthly", "Rarely"));

                Question q8 = new Question(
                                "If you won $10,000 tomorrow, what would you do first?",
                                Arrays.asList("Invest it", "Put it in savings", "Book a vacation or buy something fun",
                                                "Pay off debt"));

                // Example of Array.asList
                /*
                 * This line:
                 * 
                 * Arrays.asList("A", "B", "C")
                 * 
                 * Does the same thing as:
                 * 
                 * List<String> options = new ArrayList<>();
                 * options.add("A");
                 * options.add("B");
                 * options.add("C");
                 */

                return Arrays.asList(q1, q2, q3, q4, q5, q6, q7, q8);
        }

        // This method wil run when frontend does a POST to /api/quiz/submit
        @PostMapping("/submit")
        public PersonalityType submitQuiz(@RequestBody List<String> userAnswers) {
                System.out.println("User Answers:" + userAnswers);

                PersonalityType result = quizService.calculateResult(userAnswers);
                return result;
        }

        //will be used when the frontend (the result page) asks for data for the chart
        @GetMapping("/personality-type-counts")
        public Map<String, Long> getPersonalityTypeCounts() {
                return quizService.getPersonalityTypeCounts();
        }

}
