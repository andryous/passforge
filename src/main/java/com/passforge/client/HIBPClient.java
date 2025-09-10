package com.passforge.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HIBPClient {

    private static final String HIBP_API_URL = "https://api.pwnedpasswords.com";
    private final WebClient webClient;

    public HIBPClient() {
        this.webClient = WebClient.builder()
                .baseUrl(HIBP_API_URL)
                .build();
    }

    /**
     * Checks if a password has been exposed in a data breach using the k-Anonymity model.
     * @param password The plain text password to check.
     * @return A boolean indicating if the password was found in a breach.
     */
    public boolean isPasswordPwned(String password) {
        // Step 1: Get the SHA-1 hash of the password using our new helper method.
        String sha1Hash = sha1Hash(password);

        // Step 2: Split the hash into a prefix and suffix.
        String hashPrefix = sha1Hash.substring(0, 5);
        String hashSuffix = sha1Hash.substring(5);

        // Step 3: Make the API call.
        String responseBody = this.webClient.get()
                .uri("/range/" + hashPrefix)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Step 4: Check if the response contains the hash suffix.
        if (responseBody != null) {
            // The response is a list of suffixes, each on a new line. We check if our suffix is present.
            return responseBody.contains(hashSuffix);
        }

        return false; // The password was not found or there was an error.
    }

    /**
     * A private helper method to convert a string to its SHA-1 hash representation.
     * @param input The string to hash.
     * @return The SHA-1 hash as an uppercase hexadecimal string.
     */
    private String sha1Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(input.getBytes());
            byte[] messageDigest = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // In a real production app, this should be handled with a proper logging framework.
            System.err.println("SHA-1 algorithm not found: " + e.getMessage());
            // We return an empty string to ensure the check fails gracefully.
            return "";
        }
    }
}