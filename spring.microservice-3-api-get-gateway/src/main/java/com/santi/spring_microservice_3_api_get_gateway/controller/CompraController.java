package com.santi.spring_microservice_3_api_get_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.santi.spring_microservice_3_api_get_gateway.request.CompraServiceRequest;
import com.santi.spring_microservice_3_api_get_gateway.security.UserPrincipal;

@RestController
@RequestMapping("gateway/compra")
public class CompraController {

    @Autowired
    private CompraServiceRequest compraServiceRequest;

    @PostMapping
    public ResponseEntity<?> saveCompra(@RequestBody Object compra){
        return new ResponseEntity<>(compraServiceRequest.saveCompra(compra), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllComprasOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(compraServiceRequest.getAllComprasOfUser(userPrincipal.getId()));
    }

}
