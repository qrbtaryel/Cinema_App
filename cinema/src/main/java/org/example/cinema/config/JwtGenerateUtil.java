package org.example.cinema.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtGenerateUtil {

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";

    @Value("${jwt.access_token_time}")
    private Long accessTokenTime;

    @Value("${jwt.refresh_token_time}")
    private Long refreshTokenTime;

    @Value("${jwt.access_secret_key}")
    private String accessSecretKey;

    @Value("${jwt.refresh_secret_key}")
    private String refreshSecretKey;


    public Algorithm getAlgorithm(String key) {
        return Algorithm.HMAC512(key);
    }

    public String createAccessToken(Long userId, HttpServletResponse response) {
        String accessToken = JWT.create()
                .withSubject(String.valueOf(userId))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenTime))
                .sign(getAlgorithm(accessSecretKey));
        setCookie(response, ACCESS_TOKEN, accessToken, Math.toIntExact(accessTokenTime));

        return accessToken;
    }

    public String createRefreshToken(Long userId, HttpServletResponse response) {

        Algorithm algorithm = getAlgorithm(refreshSecretKey);
        String refreshToken = JWT.create()
                .withSubject(String.valueOf(userId))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenTime))
                .sign(algorithm);
        setCookie(response, REFRESH_TOKEN, refreshToken, Math.toIntExact(refreshTokenTime));
        return refreshToken;
    }

    private void setCookie(HttpServletResponse response, String name, String value, int expiry) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Ensure this is true in production
        cookie.setPath("/"); // Set the path for the cookie
        cookie.setMaxAge(expiry / 1000); // 1 day
        response.addCookie(cookie);
    }

    public Long decodeAccessToken(String token) {
        Algorithm algorithm = getAlgorithm(accessSecretKey);
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        return Long.valueOf(decodedJWT.getSubject());
    }

    public Long decodeRefreshToken(String token) {
        try {
            Algorithm algorithm = getAlgorithm(refreshSecretKey);
            var decodedJwt = JWT.require(algorithm).build().verify(token);

            return Long.valueOf(decodedJwt.getSubject());
        } catch (Exception e) {
            throw new ResponseStatusException(FORBIDDEN, "Istifade muddetiniz bitmisdir, yeniden daxil olun zehmet olmazsa");
        }
    }
}