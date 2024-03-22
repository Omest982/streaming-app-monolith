package org.example.service;

import org.example.dto.FollowRequestDto;
import org.example.entity.Follow;

import java.util.UUID;

public interface FollowService {
    UUID followUser(FollowRequestDto followRequestDto);
    Follow getFollowById(UUID followId);
}
