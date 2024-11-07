package com.santi.spring_boot_microservices_1_inmueble.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="inmueble")
public class Inmueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "direccion", length = 500, nullable = false)
    private String direccion;

    @Column(name = "foto", length = 1200, nullable = true)
    private String picture;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "fecha_creciacion", nullable = false)
    private LocalDateTime fechaCreciacion;
}
