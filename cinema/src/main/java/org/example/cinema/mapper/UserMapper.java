package org.example.cinema.mapper;

import org.example.cinema.dto.request.UserRequest;
import org.example.cinema.dto.response.UserResponse;
import org.example.cinema.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}
