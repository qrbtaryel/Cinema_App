package org.example.cinema.mapper;

import org.example.cinema.dto.request.SessionRequest;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.model.Session;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    Session toSession(SessionRequest sessionRequest);
    SessionResponse toSessionResponse(Session session);
}
