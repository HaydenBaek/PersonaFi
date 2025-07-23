package com.personafi.backend;

import com.personafi.backend.model.PersonalityType;
import com.personafi.backend.repository.QuizSubmissionRepository;
import com.personafi.backend.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizServiceTest {

    @Mock
    private QuizSubmissionRepository submissionRepository;

    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateResult_AllPlannerAnswers() {
        List<String> answers = List.of(
            "Love it",
            "Long-term, steady growth",
            "Stick to a pre-planned list",
            "Saving for future goals",
            "Daily"
        );

        PersonalityType result = quizService.calculateResult(answers);
        assertEquals("Planner", result.getType());
        assertNotNull(result.getDescription());
        assertNotNull(result.getTips());
    }

    @Test
    void testCalculateResult_AllInvestorAnswers() {
        List<String> answers = List.of(
            "Check investments",
            "High-risk, high-reward",
            "Monthly",
            "Invest it",
            "Growing wealth"
        );

        PersonalityType result = quizService.calculateResult(answers);
        assertEquals("Investor", result.getType());
        assertNotNull(result.getDescription());
        assertNotNull(result.getTips());
    }

    @Test
    void testCalculateResult_AllSpenderAnswers() {
        List<String> answers = List.of(
            "Buy something fun",
            "Ignore it",
            "Hate it",
            "Buy based on mood",
            "Book a vacation or buy something fun"
        );

        PersonalityType result = quizService.calculateResult(answers);
        assertEquals("Spontaneous Spender", result.getType());
        assertNotNull(result.getDescription());
        assertNotNull(result.getTips());
    }

    @Test
    void testCalculateResult_AllSecuritySeekerAnswers() {
        List<String> answers = List.of(
            "I avoid investing",
            "Stability and safety",
            "Avoid it at all costs",
            "Put it in savings",
            "Pay off debt"
        );

        PersonalityType result = quizService.calculateResult(answers);
        assertEquals("Security Seeker", result.getType());
        assertNotNull(result.getDescription());
        assertNotNull(result.getTips());
    }

    @Test
    void testCalculateResult_EmptyInput() {
        List<String> answers = List.of();
        assertThrows(IllegalArgumentException.class, () -> {
            quizService.calculateResult(answers);
        });
    }
}
