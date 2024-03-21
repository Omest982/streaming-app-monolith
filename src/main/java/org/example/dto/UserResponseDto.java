package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponseDto {
    private UUID id;
    private String avatarUrl;
    private String username;
}
