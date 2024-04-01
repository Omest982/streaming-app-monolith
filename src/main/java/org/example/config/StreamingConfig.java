package org.example.config;

import io.livekit.server.AccessToken;
import io.livekit.server.IngressServiceClient;
import io.livekit.server.RoomServiceClient;
import io.livekit.server.WebhookReceiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StreamingConfig {
    @Value("${livekit.secret-key}")
    private String secretKey;
    @Value("${livekit.api-key}")
    private String apiKey;
    @Value("${livekit.host-http}")
    private String liveKitHost;

    @Bean
    public IngressServiceClient ingressServiceClient(){
        return IngressServiceClient.create(
                liveKitHost,
                apiKey,
                secretKey
        );
    }
    @Bean
    public RoomServiceClient roomServiceClient(){
        return RoomServiceClient.create(
                liveKitHost,
                apiKey,
                secretKey
        );
    }

    @Bean
    public WebhookReceiver webhookReceiver() {
        return new WebhookReceiver(apiKey, secretKey);
    }

}
