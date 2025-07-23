package com.personafi.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personafi.backend.model.PersonalityType;
import com.personafi.backend.service.QuizService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSubmitQuiz_ReturnsPlanner() throws Exception {
        List<String> answers = List.of(
            "Love it",
            "Long-term, steady growth",
            "Stick to a pre-planned list",
            "Saving for future goals",
            "Daily"
        );

        PersonalityType expected = new PersonalityType(
                "Planner",
                "You like setting goals and organizing your finances. You’re detail-oriented and love having control over your money.",
                "Set clear milestones for your savings and investment goals. Use budgeting apps to stay on track."
        );

        Mockito.when(quizService.calculateResult(answers)).thenReturn(expected);

        mockMvc.perform(post("/api/quiz/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(answers)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Planner"))
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.tips").exists());
    }

    @Test
    void testSubmitQuiz_EmptyInput_Returns400() throws Exception {
        List<String> emptyAnswers = List.of();

        Mockito.when(quizService.calculateResult(emptyAnswers))
                .thenThrow(new IllegalArgumentException("Answers list cannot be null or empty"));

        mockMvc.perform(post("/api/quiz/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emptyAnswers)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSubmitQuiz_MalformedJson_Returns400() throws Exception {
        String malformedJson = "{\"notAList\":\"value\"}";

        mockMvc.perform(post("/api/quiz/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSubmitQuiz_UnknownAnswers_ReturnsFallbackType() throws Exception {
        List<String> unknownAnswers = List.of("???", "???", "???");

        PersonalityType fallback = new PersonalityType(
                "Unknown",
                "We couldn't determine your personality type.",
                "Try retaking the quiz."
        );

        Mockito.when(quizService.calculateResult(unknownAnswers)).thenReturn(fallback);

        mockMvc.perform(post("/api/quiz/submit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(unknownAnswers)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Unknown"));
    }
}
