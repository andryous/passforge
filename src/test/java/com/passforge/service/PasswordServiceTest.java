package com.passforge.service;

import org.junit.jupiter.api.Test; // Import for the @Test annotation
import static org.junit.jupiter.api.Assertions.*; // Import for assertion methods

class PasswordServiceTest {

    // A test method is marked with the @Test annotation.
    // The name should clearly describe what it's testing.
    @Test
    void determineStrengthLevel_ShouldReturnWeak_ForLowScores() {
        // ARRANGE: Set up the necessary objects and variables.
        // In this case, we don't need a full PasswordService with dependencies,
        // so we can create a "dummy" one for this simple test.
        PasswordService passwordService = new PasswordService(null, null);
        int lowScore = 35;

        // ACT: Call the method that we want to test.
        String result = passwordService.determineStrengthLevel(lowScore);

        // ASSERT: Check if the result is what we expected.
        // assertEquals checks if the two values are equal. If not, the test fails.
        assertEquals("WEAK", result);
    }

    @Test
    void determineStrengthLevel_ShouldReturnStrong_ForHighScores() {
        // ARRANGE
        PasswordService passwordService = new PasswordService(null, null);
        int highScore = 78;

        // ACT
        String result = passwordService.determineStrengthLevel(highScore);

        // ASSERT
        assertEquals("STRONG", result);
    }
}