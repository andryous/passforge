package com.passforge.controller;

import com.passforge.dto.PasswordAnalysisRequest;
import com.passforge.dto.PasswordAnalysisResponse;
import com.passforge.service.PasswordService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin; // 1. ADD THIS IMPORT

@RestController
@RequestMapping("/api/passwords")
@CrossOrigin(origins = {"http://localhost:5173", "https://passforge-frontend.vercel.app"})
public class PasswordController {

    // Field for the PasswordService instance.
    // Marked as 'final' to ensure it is immutable after initialization.
    private final PasswordService passwordService;

    // This is the class CONSTRUCTOR.
    // Spring uses this constructor to inject a PasswordService instance
    // when creating the PasswordController. This is called Constructor-based Dependency Injection.
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    // A health check endpoint to verify that the application is up and running.
    @GetMapping("/health")
    public String healthCheck() {
        return "API is up and running!";
    }

    // @PostMapping: Maps HTTP POST requests to this specific URL.
    // Used when the client needs to send data in the request body to be processed.
    @PostMapping("/analyze")
    public PasswordAnalysisResponse analyzePassword(@RequestBody PasswordAnalysisRequest request) {
        return passwordService.analyzePassword(request.getPassword());
    }
}