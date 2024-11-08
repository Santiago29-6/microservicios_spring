package com.santi.spring_boot_microservices_1_inmueble.repository;
import com.santi.spring_boot_microservices_1_inmueble.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InmuebleRepository extends JpaRepository<Inmueble, Long>{

}
