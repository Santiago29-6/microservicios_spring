package com.santi.spring_microservice_3_api_get_gateway.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.santi.spring_microservice_3_api_get_gateway.model.Role;
import com.santi.spring_microservice_3_api_get_gateway.model.User;

@Service
public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void chageRole(Role newRole, String username);
}
