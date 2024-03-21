package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String password;
    private boolean isLive;

    private String serverUrl;
    private String streamKey;
    private String ingressId;

    private String thumbnaiUrl;
}
