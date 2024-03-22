package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dto.FollowRequestDto;
import org.example.entity.Follow;
import org.example.service.FollowService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Follow service", description = "Endpoints for follows")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController {
    private final FollowService followService;

    @Operation(summary = "Follow", description = "User can follow other user")
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public UUID follow(@RequestBody FollowRequestDto followRequestDto){
        return followService.followUser(followRequestDto);
    }

    @Operation(summary = "Get follow by id")
    @GetMapping("/{id}")
    public Follow getFollowById(@PathVariable(name = "id") UUID followId){
        return followService.getFollowById(followId);
    }
}
