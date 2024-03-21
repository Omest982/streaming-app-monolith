package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(UUID.fromString(id));
    }

    @GetMapping("/me")
    public UserResponseDto getCurrentUser(@RequestHeader("Authorization") String bearerToken){
        return null;
    }

    @PostMapping
    public UUID addUser(@RequestBody UserDto userDto){
        User user = User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .isLive(userDto.isLive())
                .ingressId(userDto.getIngressId())
                .password(userDto.getPassword())
                .serverUrl(userDto.getServerUrl())
                .thumbnaiUrl(userDto.getThumbnaiUrl())
                .streamKey(userDto.getStreamKey())
                .build();
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public UUID updateUser(@RequestBody UserUpdateDto userUpdateDto,
                           @PathVariable String id){
        User user = userService.getUserById(UUID.fromString(id));
        user.setServerUrl(userUpdateDto.getServerUrl());
        user.setIngressId(userUpdateDto.getIngressId());
        user.setStreamKey(userUpdateDto.getStreamKey());

        return userService.saveUser(user);
    }
}
