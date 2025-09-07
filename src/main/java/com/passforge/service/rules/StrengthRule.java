package com.passforge.service.rules;

import com.passforge.dto.PasswordAnalysisResponse;

/**
 * This interface defines the contract for all password strength rules.
 */
public interface StrengthRule {

    /**
     * Applies a specific strength rule to the password and updates the analysis response.
     * @param response The response object to be modified with the rule's results.
     * @param password The password being analyzed.
     */
    void apply(PasswordAnalysisResponse response, String password);
}