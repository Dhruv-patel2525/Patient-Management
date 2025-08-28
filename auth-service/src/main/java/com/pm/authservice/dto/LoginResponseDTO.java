package com.pm.authservice.dto;

import org.springframework.security.core.parameters.P;

public class LoginResponseDTO {
    private final String token;
    public LoginResponseDTO(String token)
    {
        this.token=token;
    }

    public String getToken() {
        return token;
    }
}
