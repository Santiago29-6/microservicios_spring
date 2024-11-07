package com.santi.spring_microservice_3_api_get_gateway.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santi.spring_microservice_3_api_get_gateway.model.User;
import com.santi.spring_microservice_3_api_get_gateway.service.UserService;
import com.santi.spring_microservice_3_api_get_gateway.utils.SecurityUtils;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
        .orElseThrow( () ->new UsernameNotFoundException("El usuario no fue encontrado:" + username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrincipal.builder()
            .user(user)
            .id(user.getId())
            .username(username)
            .password(user.getPassword())
            .authorities(authorities)
            .build();
    }



}