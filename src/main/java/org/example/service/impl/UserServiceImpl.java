package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.Follow;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.utils.LiveKitTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LiveKitTest liveKitTest;
    private final UserMapper userMapper;
    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("User with id %s not found", userId)));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("User with username %s not found", username)));
    }

    @Override
    public UUID updateUser(UserUpdateDto userUpdateDto, UUID userId) {
        User user = getUserById(userId);
        user.setServerUrl(userUpdateDto.getServerUrl());
        user.setIngressId(userUpdateDto.getIngressId());
        user.setStreamKey(userUpdateDto.getStreamKey());

        return saveUser(user);
    }

    @Override
    public boolean isUserExistsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public UUID saveUser(User user) {
        try {
            liveKitTest.setupConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userRepository.save(user).getId();
    }

    @Override
    public UserResponseDto getCurrentUserProfileInfo() {
        User user = getCurrentUser();
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    @Override
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
