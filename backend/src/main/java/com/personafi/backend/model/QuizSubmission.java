package com.personafi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.personafi.backend.repository.QuizSubmissionRepository;

@Entity
public class QuizSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime submittedAt;

    @ElementCollection
    private List<String> userAnswers;

    private String personalityType;

    public QuizSubmission() {
    }

    public QuizSubmission(LocalDateTime submittedAt, List<String> userAnswers, String personalityType) {
        this.submittedAt = submittedAt;
        this.userAnswers = userAnswers;
        this.personalityType = personalityType;
    }



    // Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public String getPersonalityType() {
        return personalityType;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void setUserAnswers(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void setPersonalityType(String personalityType) {
        this.personalityType = personalityType;
    }
}
