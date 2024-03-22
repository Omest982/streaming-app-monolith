package org.example.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FollowRequestDto {
    private UUID followedUserId;
}
