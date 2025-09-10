package com.passforge.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

// We remove @AllArgsConstructor because we will manage object creation more manually now.
@Data
public class PasswordAnalysisResponse {

    private int score;
    private String strengthLevel;
    private boolean pwned;

    // A list to hold actionable suggestions for the user.
    // We initialize it to an empty list to avoid null pointer exceptions.
    private List<String> suggestions = new ArrayList<>();

    // We create a constructor for the initial state.
    public PasswordAnalysisResponse(int score, String strengthLevel, boolean pwned) {
        this.score = score;
        this.strengthLevel = strengthLevel;
        this.pwned = pwned;
    }
}