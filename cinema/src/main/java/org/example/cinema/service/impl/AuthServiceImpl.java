package org.example.cinema.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.cinema.config.JwtGenerateUtil;
import org.example.cinema.dto.request.AuthRequest;
import org.example.cinema.model.User;
import org.example.cinema.service.AuthService;
import org.example.cinema.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtGenerateUtil jwtGenerateUtil;


    @Override
    @Transactional
    public void login(AuthRequest authRequest, HttpServletResponse httpResponse) {
        User user = userService.validateUser(authRequest);
        Long userId = user.getId();
        var authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        jwtGenerateUtil.createAccessToken(userId, httpResponse);
        jwtGenerateUtil.createRefreshToken(userId, httpResponse);
    }

    @Override
    @Transactional
    public void refresh(String refreshToken, HttpServletResponse httpResponse) {
        Long userId = jwtGenerateUtil.decodeRefreshToken(refreshToken);
        jwtGenerateUtil.createAccessToken(userId, httpResponse);
        jwtGenerateUtil.createRefreshToken(userId, httpResponse);
    }
}
