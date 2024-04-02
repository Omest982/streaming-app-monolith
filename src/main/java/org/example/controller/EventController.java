package org.example.controller;

import io.livekit.server.WebhookReceiver;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import livekit.LivekitWebhook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Event service", description = "Endpoints for livekit webhook events")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/event")
@Slf4j
@RestController
public class EventController {
    private final WebhookReceiver webhookReceiver;

    @Operation(summary = "Test endpoint for webhook")
    @PostMapping(value = "/webhook", consumes = "application/webhook+json")
    public String testEvent(@RequestBody String postBody){
        System.out.println("Works");
        LivekitWebhook.WebhookEvent event = webhookReceiver.receive(postBody, null, true);
        log.info(event.getEvent());
        return event.getEvent();
    }
}
