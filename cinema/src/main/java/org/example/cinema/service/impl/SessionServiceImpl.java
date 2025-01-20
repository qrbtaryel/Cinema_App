package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.SessionRequest;
import org.example.cinema.dto.response.SessionResponse;
import org.example.cinema.mapper.SessionMapper;
import org.example.cinema.model.Session;
import org.example.cinema.repository.SessionRepository;
import org.example.cinema.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionMapper sessionMapper;
    private final SessionRepository sessionRepository;

    @Override
    public List<SessionResponse> getSessions() {
        return sessionRepository.findAll().stream().map(sessionMapper::toSessionResponse).toList();
    }

    @Override
    public SessionResponse createSession(SessionRequest sessionRequest) {
        Session session = sessionMapper.toSession(sessionRequest);
        Session newSession = sessionRepository.save(session);
        return sessionMapper.toSessionResponse(newSession);
    }

    @Override
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }
}
