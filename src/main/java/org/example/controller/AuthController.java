package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.AuthRequest;
import org.example.dto.RegisterRequest;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Authentication", description = "Endpoints for authentication")
@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private  final AuthService authService;
    @Operation(summary = "Authenticate", description = "Users can authenticate using their credentials")
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }

    @Operation(summary = "Registration", description = "Users can register to app")
    @PostMapping("/register")
    public UUID register(@RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    @Operation(summary = "Validation", description = "Developer can check token validity")
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam(name = "token") String token){
        log.info("Started checking token validity");
        if (authService.validateToken(token)){
            log.info("Finished checked token");
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
