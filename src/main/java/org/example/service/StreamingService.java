package org.example.service;

import org.example.dto.StreamingInfoResponse;

import java.io.IOException;

public interface StreamingService {
    StreamingInfoResponse getStreamingInfo();
    String getAccessToken(String roomName);
    String getRoom(String roomName) throws IOException;
}
