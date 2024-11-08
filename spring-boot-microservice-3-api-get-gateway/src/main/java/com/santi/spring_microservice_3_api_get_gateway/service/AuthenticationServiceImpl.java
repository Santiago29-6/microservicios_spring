package com.santi.spring_microservice_3_api_get_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.santi.spring_microservice_3_api_get_gateway.model.User;
import com.santi.spring_microservice_3_api_get_gateway.security.UserPrincipal;
import com.santi.spring_microservice_3_api_get_gateway.security.jwt.JwtProvider;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User signInAndReturnJwt(User singInRequest){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(singInRequest.getUsername(), singInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User singInUser = userPrincipal.getUser();
        singInUser.setToken(jwt);

        return singInUser;
    }
}
