package com.passforge.service.rules;

import com.passforge.dto.PasswordAnalysisResponse;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommonPasswordRule implements StrengthRule {

    // A Set is used because it provides very fast 'contains' checks.
    private final Set<String> commonPasswords = new HashSet<>();

    // @PostConstruct is a Spring annotation that runs this method once,
    // right after the component has been created. It's perfect for initialization tasks.
    @PostConstruct
    public void init() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("common-passwords.txt")) {
            if (is == null) {
                System.err.println("Could not find common-passwords.txt");
                return;
            }
            // Read the file and load all passwords into our Set.
            this.commonPasswords.addAll(
                    new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.toSet())
            );
        } catch (Exception e) {
            // In a real app, use a proper logger.
            System.err.println("Error reading common-passwords.txt: " + e.getMessage());
        }
    }

    @Override
    public void apply(PasswordAnalysisResponse response, String password) {
        if (commonPasswords.contains(password.toLowerCase())) {
            response.setScore(5);
            response.setStrengthLevel("VERY_WEAK"); // For common passwords, we override the level directly.
            response.getSuggestions().add("This password is too common and can be easily guessed.");
        }
    }
}