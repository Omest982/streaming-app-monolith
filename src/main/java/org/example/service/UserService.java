package org.example.service;

import org.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    User getUserById(UUID userId);
    User getUserByUsername(String username);
    boolean isUserExistsByUsernameOrEmail(String username, String email);
    UUID saveUser(User user);
}
