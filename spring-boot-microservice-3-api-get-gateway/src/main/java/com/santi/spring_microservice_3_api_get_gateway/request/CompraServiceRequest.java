package com.santi.spring_microservice_3_api_get_gateway.request;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
    value="compra-service",
    path="api/compra",
    //url = "${compras.service.url}",
    configuration = FeignConfiguration.class
)
public interface CompraServiceRequest {

    @PostMapping
    Object saveCompra(@RequestBody Object requesBody);

    @GetMapping("{userId}")
    List<Object> getAllComprasOfUser(@PathVariable("userId") Long userId);
    
}
