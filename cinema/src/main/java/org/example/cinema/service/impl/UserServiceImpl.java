package org.example.cinema.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.cinema.dto.request.AuthRequest;
import org.example.cinema.dto.request.UserRequest;
import org.example.cinema.dto.response.UserResponse;
import org.example.cinema.mapper.UserMapper;
import org.example.cinema.model.User;
import org.example.cinema.repository.UserRepository;
import org.example.cinema.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static org.example.cinema.config.SecurityUtil.getCurrentUserId;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findCurrentUser() {
        Long currentUserId = getCurrentUserId();
        return userRepository.findById(currentUserId).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Istifadəçi tapılmadı."));
    }

    @Override
    public User validateUser(AuthRequest authRequest) {
        return userRepository.findByUsername(authRequest.getUsername()).map(user -> {
            if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                throw new ResponseStatusException(BAD_REQUEST, "Istifadeci adi ve ya sifreniz yanlisdir");
            }
            return user;
        }).orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Istifadeci adi ve ya sifreniz yanlisdir"));
    }

    @Override
    @Transactional
    public UserResponse save(UserRequest request) {
        User user = userMapper.toUser(request);
        user.setBalance(BigDecimal.valueOf(100));
        User newUser = userRepository.save(user);
        return userMapper.toUserResponse(newUser);
    }

    @Override
    public void reduceBalance(User currentUser, BigDecimal price) {
        BigDecimal remainderBalance = currentUser.getBalance().subtract(price);
        currentUser.setBalance(remainderBalance);
        userRepository.save(currentUser);
    }

    @Override
    public UserResponse convertUserResponse(User user) {
        return userMapper.toUserResponse(user);
    }

    @Override
    public void increaseBalance(User currentUser, BigDecimal price) {
        BigDecimal remainderBalance = currentUser.getBalance().add(price);
        currentUser.setBalance(remainderBalance);
        userRepository.save(currentUser);
    }
}
