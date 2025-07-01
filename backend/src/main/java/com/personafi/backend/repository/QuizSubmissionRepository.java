package com.personafi.backend.repository;

import com.personafi.backend.model.QuizSubmission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Long> {


    //getting how many people are for each personality
    @Query("SELECT q.personalityType, COUNT(q) FROM QuizSubmission q GROUP BY q.personalityType")
    List<Object[]> countByPersonalityType();

}
