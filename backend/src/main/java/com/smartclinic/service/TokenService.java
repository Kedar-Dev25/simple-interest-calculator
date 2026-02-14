package com.smartclinic.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    // Dummy method to validate token
    public boolean validateToken(String token) {
        // Normally yahan JWT ya DB validation hoti
        // Abhi ke liye sirf placeholder
        return token != null && token.equals("valid-token");
    }
}
