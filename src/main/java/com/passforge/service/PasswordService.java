package com.passforge.service;

import com.passforge.dto.PasswordAnalysisResponse;
import com.passforge.service.rules.StrengthRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    // A field to hold all the rule components that Spring finds.
    private final List<StrengthRule> rules;

    // Constructor Injection: Spring will automatically find all beans that
    // implement the StrengthRule interface and inject them here as a List.
    public PasswordService(List<StrengthRule> rules) {
        this.rules = rules;
    }

    /**
     * Analyzes the strength of a given password by applying a list of rules.
     * @param password The password string to be analyzed.
     * @return A PasswordAnalysisResponse object containing the analysis results.
     */
    public PasswordAnalysisResponse analyzePassword(String password) {
        // Start with a default response object with a base score of 0.
        PasswordAnalysisResponse response = new PasswordAnalysisResponse(0, "UNKNOWN");

        // Loop through each rule in the list and apply it.
        // Each rule will modify the response object by adding score and setting a level.
        for (StrengthRule rule : rules) {
            rule.apply(response, password);
        }

        return response;
    }
}