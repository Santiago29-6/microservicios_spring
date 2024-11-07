package com.santi.spring_microservice_3_api_get_gateway.security.jwt;

import org.springframework.security.core.Authentication;

import com.santi.spring_microservice_3_api_get_gateway.model.User;
import com.santi.spring_microservice_3_api_get_gateway.security.UserPrincipal;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);
    Authentication getAuthentication(HttpServletRequest request);
    boolean isTokenValid(HttpServletRequest request);
    String generateToken(User user);
}
