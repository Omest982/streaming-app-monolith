package org.example.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.FollowRequestDto;
import org.example.entity.Follow;
import org.example.entity.User;
import org.example.repository.FollowRepository;
import org.example.service.FollowService;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final UserService userService;
    private final FollowRepository followRepository;
    @Override
    public UUID followUser(FollowRequestDto followRequestDto) {
        UUID followedUserId = followRequestDto.getFollowedUserId();
        User currentUser = userService.getCurrentUser();
        User followedUser = userService.getUserById(followedUserId);

        Follow follow = Follow.builder()
                .followedBy(currentUser)
                .following(followedUser)
                .build();

        return followRepository.save(follow).getId();
    }

    @Override
    public Follow getFollowById(UUID followId) {
        return followRepository.findById(followId).orElseThrow(()->
                new EntityNotFoundException(String.format("Follow with id %s not found!", followId)));
    }
}
