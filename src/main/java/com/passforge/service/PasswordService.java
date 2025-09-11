package com.passforge.service;

import com.passforge.client.HIBPClient; // Import our new client
import com.passforge.dto.PasswordAnalysisResponse;
import com.passforge.service.rules.StrengthRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    private final List<StrengthRule> rules;
    private final HIBPClient hibpClient; // Add a field for our new client

    // Update the constructor to also inject the HIBPClient.
    // Spring will find the @Component-annotated HIBPClient and pass it in automatically.
    public PasswordService(List<StrengthRule> rules, HIBPClient hibpClient) {
        this.rules = rules;
        this.hibpClient = hibpClient;
    }

    /**
     * Analyzes a password's strength and checks if it has been pwned.
     * @param password The password string to be analyzed.
     * @return A PasswordAnalysisResponse object with the full analysis.
     */
    public PasswordAnalysisResponse analyzePassword(String password) {
        // Start with a default response object.
        PasswordAnalysisResponse response = new PasswordAnalysisResponse(0, "UNKNOWN", false);

        // Loop through each strength rule.
        for (StrengthRule rule : rules) {
            rule.apply(response, password);
        }

        // Determine the final strength level based on the total score.
        String finalStrengthLevel = determineStrengthLevel(response.getScore());
        response.setStrengthLevel(finalStrengthLevel);

        // Call the HIBPClient to check for breaches.
        boolean isPwned = hibpClient.isPasswordPwned(password);
        response.setPwned(isPwned);

        return response;
    }

    /**
     * A private helper method to determine the strength level based on a numeric score.
     * @param score The final calculated score.
     * @return A string representing the strength level.
     */
    public String determineStrengthLevel(int score) {
        if (score < 40) {
            return "WEAK";
        } else if (score < 60) {
            return "MEDIUM";
        } else if (score < 80) {
            return "STRONG";
        } else {
            return "VERY_STRONG";
        }
    }
}