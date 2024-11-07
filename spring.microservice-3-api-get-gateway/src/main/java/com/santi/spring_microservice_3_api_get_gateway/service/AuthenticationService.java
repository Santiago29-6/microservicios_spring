package com.santi.spring_microservice_3_api_get_gateway.service;

import com.santi.spring_microservice_3_api_get_gateway.model.User;

public interface AuthenticationService {
    User signInAndReturnJwt(User singInRequest);
}
