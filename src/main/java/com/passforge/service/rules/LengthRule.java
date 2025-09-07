package com.passforge.service.rules;

import com.passforge.dto.PasswordAnalysisResponse;
import org.springframework.stereotype.Component;

// @Component marks this class as a generic Spring component,
// allowing it to be managed by the Spring container and injected where needed.
@Component
public class LengthRule implements StrengthRule {

    @Override
    public void apply(PasswordAnalysisResponse response, String password) {
        int length = password.length();

        if (length < 8) {
            response.setScore(response.getScore() + 10); // Adds 10 points
            response.setStrengthLevel("VERY_WEAK");
        } else if (length < 12) {
            response.setScore(response.getScore() + 25); // Adds 25 points
            response.setStrengthLevel("MEDIUM");
        } else {
            response.setScore(response.getScore() + 40); // Adds 40 points
            response.setStrengthLevel("STRONG");
        }
    }
}