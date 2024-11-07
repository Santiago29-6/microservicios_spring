package com.santi.spring_boot_microservice_compra.service;

import java.util.List;

import com.santi.spring_boot_microservice_compra.model.Compra;

public interface CompraService {
    Compra saveCompra(Compra compra);
    List<Compra> findAllComprasOfUser(Long userId);
}
