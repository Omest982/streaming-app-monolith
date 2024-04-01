package org.example.mapper;

import org.example.dto.StreamingInfoResponse;
import org.example.dto.UserResponseDto;
import org.example.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto userToUserResponseDto(User user);

    StreamingInfoResponse userToStreamingInfoResponse(User user);
}
