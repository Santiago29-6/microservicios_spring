package com.santi.spring_boot_microservices_1_inmueble.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.santi.spring_boot_microservices_1_inmueble.model.Inmueble;
import com.santi.spring_boot_microservices_1_inmueble.repository.InmuebleRepository;

@Service
public class InmuebleServiceImpl implements InmuebleService{
    
    private final InmuebleRepository inmuebleRepository;

    public InmuebleServiceImpl(InmuebleRepository inmuebleRepository){
        this.inmuebleRepository = inmuebleRepository;
    }

    @Override
    public Inmueble saveInmueble(Inmueble inmueble)
    {
        inmueble.setFechaCreciacion(LocalDateTime.now());
        return inmuebleRepository.save(inmueble);
    }

    @Override
    public void deleteInmueble(Long inmuebleId){
        inmuebleRepository.deleteById(inmuebleId);
    }

    @Override
    public List<Inmueble> findAllInmuebles(){
        return inmuebleRepository.findAll();
    }
}
