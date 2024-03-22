package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.Follow;
import org.example.entity.User;
import org.example.entity.enums.UserRole;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User service", description = "Endpoints for user")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") UUID userId){
        return userService.getUserById(userId);
    }

    @Operation(summary = "Get current user", description = "Get user that logged in now")
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/me")
    public UserResponseDto getCurrentUserProfileInfo(){
        return userService.getCurrentUserProfileInfo();
    }

    @Operation(summary = "Update user")
    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public UUID updateUser(@RequestBody UserUpdateDto userUpdateDto,
                           @PathVariable(name = "id") UUID userId){
        return userService.updateUser(userUpdateDto, userId);
    }
}
