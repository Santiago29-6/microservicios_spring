package com.santi.spring_microservice_3_api_get_gateway.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.santi.spring_microservice_3_api_get_gateway.model.Role;
import com.santi.spring_microservice_3_api_get_gateway.model.User;
import com.santi.spring_microservice_3_api_get_gateway.repository.UserRepository;
import com.santi.spring_microservice_3_api_get_gateway.security.jwt.JwtProvider;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setFechaCreacion(LocalDateTime.now());
        User userCreated = userRepository.save(user);
        
        String jwt = jwtProvider.generateToken(userCreated);
        userCreated.setToken(jwt);

        return userCreated;
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional//cada vez que se use una sentencia sql directamente
    @Override
    public void chageRole(Role newRole, String username){
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public User findByUsernameReturnToken(String username){
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe: "+ username));
        
        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt);
        return user;
    }

}
