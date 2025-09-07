package com.passforge.dto;

import lombok.Data;

// @Data: A convenient Lombok annotation that bundles the features of
// @ToString, @EqualsAndHashCode, @Getter, and @Setter into a single annotation.
// Used to avoid writing a lot of boilerplate code.
@Data
public class PasswordAnalysisRequest {

    // This field represents the password sent by the user
    // in the JSON request body. Example: { "password": "mypassword123" }
    private String password;
}