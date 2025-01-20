package org.example.cinema.service;

import jakarta.validation.Valid;
import org.example.cinema.dto.request.AuthRequest;
import org.example.cinema.dto.request.UserRequest;
import org.example.cinema.dto.response.UserResponse;
import org.example.cinema.model.User;

import java.math.BigDecimal;

public interface UserService {
    User findCurrentUser();

    User validateUser(AuthRequest authRequest);

    UserResponse save(@Valid UserRequest request);

    void reduceBalance(User currentUser, BigDecimal price);

    UserResponse convertUserResponse(User user);

    void increaseBalance(User currentUser, BigDecimal price);
}
