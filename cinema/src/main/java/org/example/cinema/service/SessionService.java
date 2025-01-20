package org.example.cinema.service;

import org.example.cinema.dto.request.SessionRequest;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.model.Session;

import java.util.List;

public interface SessionService {
    List<SessionResponse> getSessions();

    SessionResponse createSession(SessionRequest sessionRequest);

    Session findById(Long sessionId);
}
