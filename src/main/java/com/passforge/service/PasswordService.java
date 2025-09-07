package com.passforge.service;

import com.passforge.dto.PasswordAnalysisResponse;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    /**
     * Analyzes the strength of a given password.
     * @param password The password string to be analyzed.
     * @return A PasswordAnalysisResponse object containing the analysis results.
     */
    public PasswordAnalysisResponse analyzePassword(String password) {
        int score = 0;
        String strengthLevel;

        // A very simple business logic for demonstration purposes.
        // This will be replaced by the rule-based engine.
        if (password.length() < 8) {
            score = 25;
            strengthLevel = "WEAK";
        } else {
            score = 75;
            strengthLevel = "MEDIUM";
        }

        // Creates and returns a new instance of the response DTO with the results.
        return new PasswordAnalysisResponse(score, strengthLevel);
    }
}