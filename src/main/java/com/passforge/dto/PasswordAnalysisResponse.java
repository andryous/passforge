package com.passforge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
// @AllArgsConstructor: A Lombok annotation that generates a constructor
// with an argument for every field in the class.
// This makes it easy to create new instances of this object.
@AllArgsConstructor
public class PasswordAnalysisResponse {

    // A numeric score representing the password's strength.
    private int score;

    // A descriptive text for the strength level (e.g., "WEAK", "STRONG").
    private String strengthLevel;
}