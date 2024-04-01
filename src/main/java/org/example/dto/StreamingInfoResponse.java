package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreamingInfoResponse {
    private String ingressId;
    private String streamKey;
}
