package com.personafi.backend.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personafi.backend.model.PersonalityType;
import com.personafi.backend.model.QuizSubmission;
import com.personafi.backend.repository.QuizSubmissionRepository;

@Service
public class QuizService {

    @Autowired
    private QuizSubmissionRepository submissionRepository;

    // This method will get the list of answers user submitted and output the
    // personality type
    public PersonalityType calculateResult(List<String> userAnswers) {
        if (userAnswers == null || userAnswers.isEmpty()) {
            throw new IllegalArgumentException("Answers list cannot be null or empty");
        }

        Map<String, Integer> scoreMap = new HashMap<>();

        // 6 diff types
        scoreMap.put("Planner", 0);
        scoreMap.put("Spontaneous Spender", 0);
        scoreMap.put("Saver", 0);
        scoreMap.put("Risk Taker", 0);
        scoreMap.put("Investor", 0);
        scoreMap.put("Security Seeker", 0);

        Map<String, List<String>> answerTypeMap = buildAnswerTypeMap();

        // scoring the points
        for (String answer : userAnswers) {
            List<String> types = answerTypeMap.get(answer);
            if (types != null) {
                for (String type : types) {
                    scoreMap.put(type, scoreMap.get(type) + 1);
                }
            }
        }

        String topPersonality = null;
        int maxScore = Integer.MIN_VALUE;

        // getting the top peraonltiy
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                topPersonality = entry.getKey();
            }
        }

        // saving in the db
        QuizSubmission submission = new QuizSubmission(
                LocalDateTime.now(),
                userAnswers,
                topPersonality);
        submissionRepository.save(submission);

        return getPersonalityDetails(topPersonality);

    }

    // helper method to map each answer to its personlity type
    private Map<String, List<String>> buildAnswerTypeMap() {
        Map<String, List<String>> answerTypeMap = new HashMap<>();

        // mapping each answers to each personality

        answerTypeMap.put("Transfer to savings", Arrays.asList("Saver", "Planner"));
        answerTypeMap.put("Check investments", Arrays.asList("Investor", "Risk Taker"));
        answerTypeMap.put("Buy something fun", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Ignore it", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Love it", Arrays.asList("Planner"));
        answerTypeMap.put("Hate it", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Never tried", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Do it sometimes", Arrays.asList("Saver"));

        answerTypeMap.put("High-risk, high-reward", Arrays.asList("Investor", "Risk Taker"));
        answerTypeMap.put("Long-term, steady growth", Arrays.asList("Investor", "Planner"));
        answerTypeMap.put("I avoid investing", Arrays.asList("Security Seeker"));
        answerTypeMap.put("Haven’t thought about it", Arrays.asList("Spontaneous Spender"));

        answerTypeMap.put("Compare prices and look for deals", Arrays.asList("Saver", "Planner"));
        answerTypeMap.put("Buy impulsively", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Stick to a pre-planned list", Arrays.asList("Planner"));
        answerTypeMap.put("Buy based on mood", Arrays.asList("Spontaneous Spender"));

        answerTypeMap.put("Growing wealth", Arrays.asList("Investor"));
        answerTypeMap.put("Stability and safety", Arrays.asList("Security Seeker"));
        answerTypeMap.put("Enjoying life in the moment", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Saving for future goals", Arrays.asList("Saver", "Planner"));

        answerTypeMap.put("Avoid it at all costs", Arrays.asList("Security Seeker", "Saver"));
        answerTypeMap.put("Use it strategically", Arrays.asList("Investor", "Risk Taker"));
        answerTypeMap.put("It’s just part of life", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Don’t really think about it", Arrays.asList("Spontaneous Spender"));

        answerTypeMap.put("Daily", Arrays.asList("Planner"));
        answerTypeMap.put("Weekly", Arrays.asList("Saver"));
        answerTypeMap.put("Monthly", Arrays.asList("Investor"));
        answerTypeMap.put("Rarely", Arrays.asList("Spontaneous Spender"));

        answerTypeMap.put("Invest it", Arrays.asList("Investor"));
        answerTypeMap.put("Put it in savings", Arrays.asList("Saver", "Security Seeker"));
        answerTypeMap.put("Book a vacation or buy something fun", Arrays.asList("Spontaneous Spender"));
        answerTypeMap.put("Pay off debt", Arrays.asList("Security Seeker"));

        return answerTypeMap;
    }

    // helper method to return the tips and description of a personlity
    private PersonalityType getPersonalityDetails(String type) {
        switch (type) {
            case "Planner":
                return new PersonalityType(
                        "Planner",
                        "You like setting goals and organizing your finances. You’re detail-oriented and love having control over your money.",
                        "Set clear milestones for your savings and investment goals. Use budgeting apps to stay on track.");
            case "Spontaneous Spender":
                return new PersonalityType(
                        "Spontaneous Spender",
                        "You enjoy living in the moment and treating yourself. While fun, this can sometimes lead to overspending.",
                        "Try setting a 'fun money' budget each month. Use spending trackers to stay aware.");
            case "Saver":
                return new PersonalityType(
                        "Saver",
                        "You're focused on building financial security. You love seeing your savings grow and tend to avoid unnecessary purchases.",
                        "Consider diversifying your savings into low-risk investments to grow your money safely.");
            case "Risk Taker":
                return new PersonalityType(
                        "Risk Taker",
                        "You’re not afraid to take financial risks if you see an opportunity for big rewards.",
                        "Balance your risk-taking with an emergency fund. Research before making big investment moves.");
            case "Investor":
                return new PersonalityType(
                        "Investor",
                        "Growing wealth is your main priority. You’re focused on returns and probably enjoy tracking stocks or funds.",
                        "Diversify your portfolio. Keep up with market trends, but avoid making emotional decisions.");
            case "Security Seeker":
                return new PersonalityType(
                        "Security Seeker",
                        "You value safety and financial stability. You avoid risky investments and focus on protecting your money.",
                        "Keep a strong emergency fund. Look into high-yield savings or stable bonds for safe growth.");
            default:
                return new PersonalityType(
                        "Unknown",
                        "We couldn't determine your personality type.",
                        "Try retaking the quiz.");
        }
    }

    //to map each personality count to personality type
    public Map<String, Long> getPersonalityTypeCounts() {
        List<Object[]> results = submissionRepository.countByPersonalityType();
        Map<String, Long> counts = new HashMap<>();
        for (Object[] row : results) {
            counts.put((String) row[0], (Long) row[1]);
        }
        return counts;
    }

}
