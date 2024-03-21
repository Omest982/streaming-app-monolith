package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AuthRequest;
import org.example.dto.RegisterRequest;
import org.example.entity.User;
import org.example.entity.enums.UserRole;
import org.example.security.JwtService;
import org.example.service.AuthService;
import org.example.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String authenticate(AuthRequest authRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        );

        Authentication authentication = authManager.authenticate(authToken);

        if (authentication.isAuthenticated()){
            return jwtService.generateToken(authentication);
        }
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }

    @Override
    public UUID register(RegisterRequest registerRequest) {
        if (userService.isUserExistsByUsernameOrEmail(
                registerRequest.getUsername(),
                registerRequest.getEmail())){
            throw new RuntimeException("User with this email or username already exists!");
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .username(registerRequest.getUsername())
                .userRole(UserRole.USER)
                .build();

        return userService.saveUser(user);
    }
}
