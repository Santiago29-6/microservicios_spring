package com.santi.spring_boot_microservice_compra.controller;

import org.springframework.web.bind.annotation.RestController;

import com.santi.spring_boot_microservice_compra.model.Compra;
import com.santi.spring_boot_microservice_compra.service.CompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("api/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<?> saveCompra(@RequestBody Compra compra){
        return new ResponseEntity<>(
            compraService.saveCompra(compra), 
            HttpStatus.CREATED
            );
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getAllComprasOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(compraService.findAllComprasOfUser(userId));
    }
    
}
