package com.santi.spring_microservice_3_api_get_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.spring_microservice_3_api_get_gateway.model.Role;
import com.santi.spring_microservice_3_api_get_gateway.security.UserPrincipal;
import com.santi.spring_microservice_3_api_get_gateway.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role){
        userService.chageRole(role, userPrincipal.getUsername());
        return ResponseEntity.ok(true);
        
    }
}
