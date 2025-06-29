package com.personafi.backend.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//importing my Question Model
import com.personafi.backend.model.Question;
import com.personafi.backend.service.QuizService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/quiz")
public class QuizController 
{
    @Autowired
    private QuizService quizService;
    
    @GetMapping
    public List<Question> getQuiz()
    {
        Question q1 = new Question(
            "When you get paid, what's the first thing you do?",
            Arrays.asList("Transfer to savings", "Check investments", "Buy something for fun", "Ignore it") 
        );

        Question q2 = new Question(
            "How do you feel about budgeting?",
            Arrays.asList("Love it", "Hate it", "Never tried", "Do it sometimes")
        );

        return Arrays.asList(q1, q2);

        //Example of Array.asList
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
    }
    
    //This method wil run when frontend does a POST to /api/quiz/submit
    @PostMapping("/submit")
    public String submitQuiz(@RequestBody List<String> userAnswers)
    {
        System.out.println("User Answers:" + userAnswers);

        String result = quizService.calculateResult(userAnswers);
        return result;
    }

}
