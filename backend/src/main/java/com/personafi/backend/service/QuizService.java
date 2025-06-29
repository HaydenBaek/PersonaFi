package com.personafi.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class QuizService {

    // This method will get the list of answers user submitted and output the
    // personality type
    public String calculateResult(List<String> userAnswers) {

        Map<String, Integer> scoreMap = new HashMap<>();

        int score = 0;

        scoreMap.put("Planner", 0);
        scoreMap.put("Spontaneous Spender", 0);
        scoreMap.put("Saver", 0);
        scoreMap.put("Risk Taker", 0);

        for (String answer : userAnswers) {
            switch (answer) {
                case "Transfer to savings":
                    scoreMap.put("Saver", scoreMap.get("Saver") + 1);
                    scoreMap.put("Planner", scoreMap.get("Planner") + 1);
                    break;
                case "Check investments":
                    scoreMap.put("Risk Taker", scoreMap.get("Risk Taker") + 1);
                    break;
                case "Buy something fun":
                case "Ignore it":
                    scoreMap.put("Spontaneous Spender", scoreMap.get("Spontaneous Spender") + 1);
                    break;
                case "Love it":
                    scoreMap.put("Planner", scoreMap.get("Planner") + 1);
                    break;
                case "Hate it":
                case "Never tried":
                    scoreMap.put("Spontaneous Spender", scoreMap.get("Spontaneous Spender") + 1);
                    break;
                case "Do it sometimes":
                    scoreMap.put("Saver", scoreMap.get("Saver") + 1);
                    break;
                default:
                    // Optional: Handle unknown answers
                    break;
            }
        }

        String topPersonality = null;
        int maxScore = Integer.MIN_VALUE;

        for (Map.Entry<String, Integer> entry : scoreMap.entrySet())
        {
            if (entry.getValue() > maxScore)
            {
                maxScore = entry.getValue();
                topPersonality = entry.getKey();
            }
        }

        return "Your personality type is: " + topPersonality;

    }
}
