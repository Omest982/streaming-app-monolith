package org.example.service;

import org.example.dto.UserResponseDto;
import org.example.dto.UserUpdateDto;
import org.example.entity.Follow;
import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    User getUserById(UUID userId);
    User getUserByUsername(String username);
    UUID updateUser(UserUpdateDto userUpdateDto, UUID userId);
    boolean isUserExistsByUsernameOrEmail(String username, String email);
    UUID saveUser(User user);
    UserResponseDto getCurrentUserProfileInfo();
    User getCurrentUser();
}
