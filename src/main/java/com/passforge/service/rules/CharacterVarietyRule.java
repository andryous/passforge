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
        boolean hasSymbol = password.matches(".*[!@#$%^&*()].*");

        int varietyCount = 0;
        if (hasLower) varietyCount++;
        if (hasUpper) varietyCount++;
        if (hasDigit) varietyCount++;
        if (hasSymbol) varietyCount++;

        // Add points based on how many different character types are used.
        if (varietyCount >= 3) {
            response.setScore(response.getScore() + 30);
        } else if (varietyCount >= 2) {
            response.setScore(response.getScore() + 15);
        }
    }
}