package org.example.controller;

import io.livekit.server.WebhookReceiver;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Event service", description = "Endpoints for livekit webhook events")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/stream")
@RestController
public class EventController {
    private final WebhookReceiver webhookReceiver;

    @PostMapping("/test")
    public void testEvent(){
        System.out.println("Works");
    }
}
