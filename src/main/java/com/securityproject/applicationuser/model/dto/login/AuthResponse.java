package com.securityproject.applicationuser.model.dto.login;

public record AuthResponse(String token, String type) {
    
    public AuthResponse(String token) {
        this(token, "Bearer");
    }
}
