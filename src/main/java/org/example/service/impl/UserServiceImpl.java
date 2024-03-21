package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
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
    public boolean isUserExistsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public UUID saveUser(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }
}
