// src/main/java/com/passforge/service/rules/CharacterVarietyRule.java
package com.passforge.service.rules;

import com.passforge.dto.PasswordAnalysisResponse;
import org.springframework.stereotype.Component;

@Component
public class CharacterVarietyRule implements StrengthRule {

    @Override
    public void apply(PasswordAnalysisResponse response, String password) {
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        // The regular expression includes a wider range of common symbols.
        boolean hasSymbol = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:,.<>/?].*");

        // Suggestions logic remains the same
        if (!hasLower) {
            response.getSuggestions().add("Add lowercase letters to your password.");
        }
        if (!hasUpper) {
            response.getSuggestions().add("Add uppercase letters to your password.");
        }
        if (!hasDigit) {
            response.getSuggestions().add("Add numbers to your password.");
        }
        if (!hasSymbol) {
            response.getSuggestions().add("Add symbols (e.g., !@#$%) to your password.");
        }

        int varietyCount = 0;
        if (hasLower) varietyCount++;
        if (hasUpper) varietyCount++;
        if (hasDigit) varietyCount++;
        if (hasSymbol) varietyCount++;

        // --- SCORING LOGIC CHANGE ---
        // A higher score is now awarded for using all four character types.
        if (varietyCount == 4) {
            response.setScore(response.getScore() + 45); // Increased from 30
        } else if (varietyCount == 3) {
            response.setScore(response.getScore() + 30);
        } else if (varietyCount == 2) {
            response.setScore(response.getScore() + 15);
        }
    }
}