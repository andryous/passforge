package com.passforge.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Data
public class PasswordAnalysisResponse {

    private int score;
    private String strengthLevel;
    private boolean pwned;

    // A list to hold actionable suggestions for the user.
    // It initializes it to an empty list to avoid null pointer exceptions.
    private List<String> suggestions = new ArrayList<>();

    // Constructor for the initial state.
    public PasswordAnalysisResponse(int score, String strengthLevel, boolean pwned) {
        this.score = score;
        this.strengthLevel = strengthLevel;
        this.pwned = pwned;
    }
}