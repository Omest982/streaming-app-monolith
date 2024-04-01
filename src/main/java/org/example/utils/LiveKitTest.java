package org.example.utils;

import com.google.protobuf.util.JsonFormat;
import io.livekit.server.*;
import livekit.LivekitIngress;
import livekit.LivekitModels;
import livekit.LivekitWebhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Component
@Slf4j
public class LiveKitTest {
    @Value("${livekit.secret-key}")
    private String secretKey;
    @Value("${livekit.api-key}")
    private String apiKey;
    @Value("${livekit.host-http}")
    private String liveKitHost;
    public void setupConnection()  throws IOException {
        RoomServiceClient roomServiceClient = RoomServiceClient.create(
                liveKitHost,
                apiKey,
                secretKey
        );

        IngressServiceClient ingressServiceClient = IngressServiceClient.create(
                liveKitHost,
                apiKey,
                secretKey
        );

        AccessToken token = new AccessToken(apiKey, secretKey);
        log.info(token.toJwt());

        Call<LivekitIngress.IngressInfo> ingressInfoCall =
                ingressServiceClient.createIngress(
                        "ingress-name",
                        "room-name",
                        "participant-identity",
                        "participant-name",
                        LivekitIngress.IngressInput.RTMP_INPUT
                );
        Response<LivekitIngress.IngressInfo> ingressInfoResponse = ingressInfoCall.execute();

        if (ingressInfoResponse.isSuccessful()){
            LivekitIngress.IngressInfo info = ingressInfoResponse.body();
            log.info(JsonFormat.printer().print(info));
        } else {
            log.error(String.valueOf(ingressInfoResponse.code()));
            log.error(ingressInfoResponse.message());
        }



        Call<LivekitModels.Room> call = roomServiceClient.createRoom("room_name");
        Response<LivekitModels.Room> response = call.execute(); // Use call.enqueue for async
        LivekitModels.Room room = response.body();

        //System.out.println(JsonFormat.printer().print(room));
        log.info(JsonFormat.printer().print(room));
        WebhookReceiver webhookReceiver = new WebhookReceiver(apiKey, secretKey);
        webhookReceiver.receive("room_started", null, true);

    }

}
