// src/main/java/com/passforge/service/rules/LengthRule.java
package com.passforge.service.rules;

import com.passforge.dto.PasswordAnalysisResponse;
import org.springframework.stereotype.Component;

@Component
public class LengthRule implements StrengthRule {

    @Override
    public void apply(PasswordAnalysisResponse response, String password) {
        int length = password.length();

        // --- SCORING LOGIC CHANGE ---
        // A new tier is added for passwords with 16 or more characters.
        if (length < 8) {
            response.setScore(response.getScore() + 5); // Reduced score for very short passwords
            response.getSuggestions().add("Password should be at least 8 characters long.");
        } else if (length < 12) {
            response.setScore(response.getScore() + 20); // Slightly adjusted
            response.getSuggestions().add("Consider making your password longer than 12 characters.");
        } else if (length < 16) {
            response.setScore(response.getScore() + 40);
        } else { // length >= 16
            response.setScore(response.getScore() + 55); // Highest score for very long passwords
        }
    }
}