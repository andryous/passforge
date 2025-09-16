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
        PasswordAnalysisResponse response = new PasswordAnalysisResponse(0, "UNKNOWN", false);

        // This flag will help to know if a critical rule (like CommonPassword) has already set the final state.
        boolean isOverridden = false;
        for (StrengthRule rule : rules) {
            rule.apply(response, password);
            // The CommonPasswordRule sets the level to VERY_WEAK directly. It detects that.
            if ("VERY_WEAK".equals(response.getStrengthLevel())) {
                isOverridden = true;
            }
        }

        // Only determine strength level from score if it wasn't overridden by a critical rule.
        if (!isOverridden) {
            String finalStrengthLevel = determineStrengthLevel(response.getScore());
            response.setStrengthLevel(finalStrengthLevel);
        }

        // --- NEW OVERRIDE LOGIC FOR PWNED PASSWORDS ---
        // This check happens AFTER all other rules.
        boolean isPwned = hibpClient.isPasswordPwned(password);
        response.setPwned(isPwned);

        if (isPwned) {
            // If the password is in a breach, it is fundamentally insecure.
            // Overrides any previous score or level.
            response.setStrengthLevel("COMPROMISED"); // A new, more accurate level.
            response.setScore(10); // A very low score.
            // Replace any previous suggestions with a single, critical warning.
            response.getSuggestions().clear();
            response.getSuggestions().add("This password has been exposed in a data breach and is highly insecure. Do not use it.");
        }

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