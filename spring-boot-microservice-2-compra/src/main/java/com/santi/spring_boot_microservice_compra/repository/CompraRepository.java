package com.santi.spring_boot_microservice_compra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.santi.spring_boot_microservice_compra.model.Compra;

public interface CompraRepository  extends JpaRepository<Compra, Long>{
    List<Compra> findAllByUserId(Long id);
}
