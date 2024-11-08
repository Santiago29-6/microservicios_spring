package com.santi.spring_microservice_3_api_get_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santi.spring_microservice_3_api_get_gateway.model.User;
import com.santi.spring_microservice_3_api_get_gateway.service.AuthenticationService;
import com.santi.spring_microservice_3_api_get_gateway.service.UserService;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<?> singUp(@RequestBody User user){
        if(userService.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> singIn(@RequestBody User user){
        return new ResponseEntity<>(authenticationService.signInAndReturnJwt(user), HttpStatus.OK);
    }
}
