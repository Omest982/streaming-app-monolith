package org.example.service;

import org.example.dto.AuthRequest;
import org.example.dto.RegisterRequest;

import java.util.UUID;

public interface AuthService {
    String authenticate(AuthRequest authRequest);
    boolean validateToken(String token);
    UUID register(RegisterRequest registerRequest);
}
