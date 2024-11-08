package com.santi.spring_boot_microservices_1_inmueble.service;

import java.util.List;

import com.santi.spring_boot_microservices_1_inmueble.model.Inmueble;

public interface InmuebleService {
    Inmueble saveInmueble(Inmueble inmueble);
    void deleteInmueble(Long inmuebleId);
    List<Inmueble> findAllInmuebles();
}
