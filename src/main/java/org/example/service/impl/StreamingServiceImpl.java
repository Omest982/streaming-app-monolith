package org.example.service.impl;

import com.google.protobuf.util.JsonFormat;
import io.livekit.server.*;
import livekit.LivekitIngress;
import livekit.LivekitModels;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.StreamingInfoResponse;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.StreamingService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StreamingServiceImpl implements StreamingService {
    @Value("${livekit.secret-key}")
    private String secretKey;
    @Value("${livekit.api-key}")
    private String apiKey;
    private final UserService userService;
    private final UserMapper userMapper;
    private final IngressServiceClient ingressServiceClient;
    private final RoomServiceClient roomServiceClient;

    @Override
    public StreamingInfoResponse getStreamingInfo() {
        User currentUser = userService.getCurrentUser();
        //unique for each user
        if (currentUser.getIngressId() != null &&
            currentUser.getStreamKey() != null){
            return userMapper.userToStreamingInfoResponse(currentUser);
        }

        Call<LivekitIngress.IngressInfo> ingressInfoCall =
                ingressServiceClient.createIngress(
                        "ingress-name",
                        "test",
                        currentUser.getId().toString(),
                        currentUser.getUsername(),
                        LivekitIngress.IngressInput.RTMP_INPUT
                );
        return null;
    }

    @Override
    public String getAccessToken(String roomName) {
        AccessToken token = new AccessToken(apiKey, secretKey);
        token.addGrants(new RoomJoin(true), new RoomName(roomName));

        //User user = userService.getCurrentUser();

        if (true){
            UUID randomId = UUID.randomUUID();
            token.setIdentity(randomId.toString());
            token.setName("Unauthenticated " + randomId);
        }
//        else {
//            token.setIdentity(user.getId().toString());
//            token.setName(user.getUsername());
//        }

        return token.toJwt();
    }

    @Override
    public String  getRoom(String roomName) throws IOException {
        Call<LivekitModels.Room> roomCall = roomServiceClient.createRoom(roomName);
        Response<LivekitModels.Room> roomResponse = roomCall.execute();
        LivekitModels.Room room = roomResponse.body();
        return JsonFormat.printer().print(room);
    }
}
