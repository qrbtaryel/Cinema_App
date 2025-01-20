package org.example.cinema.service;

import jakarta.servlet.http.HttpServletResponse;
import org.example.cinema.dto.request.AuthRequest;

public interface AuthService {
    void login(AuthRequest request, HttpServletResponse httpResponse);

    void refresh(String refreshToken, HttpServletResponse httpResponse);
}
