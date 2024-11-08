package com.santi.spring_microservice_3_api_get_gateway.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    value = "inmueble-service",
    path = "/api/inmueble",
    //url = "${inmueble.service.url}",
    configuration = FeignConfiguration.class
)
public interface InmuebleServiceRequest {

    @PostMapping
    Object saveInmueble(@RequestBody Object requestBody);
    
    @DeleteMapping("{inmuebleId}")
    void deleteInmueble(@PathVariable("inmuebleId") Long id);

    @GetMapping
    List<Object> getAllInmuebles();
}
