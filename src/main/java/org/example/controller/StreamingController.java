package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import livekit.LivekitModels;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.StreamingInfoResponse;
import org.example.service.StreamingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Stream service", description = "Endpoints for livekit")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/stream")
@RestController
public class StreamingController {
    private final StreamingService streamingService;

    @Operation(summary = "Get access token")
    @GetMapping("/token/{roomName}")
    public String getAccessToken(@PathVariable String roomName){
        return streamingService.getAccessToken(roomName);
    }

    @GetMapping("/room/{roomName}")
    public String getRoom(@PathVariable String roomName) throws IOException {
        return streamingService.getRoom(roomName);
    }

    @Operation(summary = "Get streaming info",
            description = "Returns streaming info for current user")
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public StreamingInfoResponse getStreamingInfo(){
        return streamingService.getStreamingInfo();
    }
}
