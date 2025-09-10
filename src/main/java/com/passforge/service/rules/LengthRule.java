package com.passforge.service.rules;

import com.passforge.dto.PasswordAnalysisResponse;
import org.springframework.stereotype.Component;

@Component
public class LengthRule implements StrengthRule {

    @Override
    public void apply(PasswordAnalysisResponse response, String password) {
        int length = password.length();

        if (length < 8) {
            response.setScore(response.getScore() + 10);
            response.getSuggestions().add("Password should be at least 8 characters long.");
        } else if (length < 12) {
            response.setScore(response.getScore() + 25);
            response.getSuggestions().add("Consider making your password longer than 12 characters.");
        } else {
            response.setScore(response.getScore() + 40);
        }
    }
}