package com.ventascafe.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cafes")
public class Cafe extends PanacheEntity {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_CAFE;
    
    @NotBlank(message = "El nombre del café es requerido")
    @Column(nullable = false)
    public String nombre;
    
    @NotBlank(message = "La descripción del café es requerida")
    @Column(nullable = false)
    public String descripcion;
    
    @NotNull(message = "El precio del café es requerido")
    @Positive(message = "El precio debe ser mayor a cero")
    @Column(nullable = false)
    public BigDecimal precio;
    
    @Column(nullable = false)
    public String origen;
    
    @NotBlank(message = "El tipo de tostado es requerido")
    @Column(name = "tipo_tostado", nullable = false)
    public String tipoTostado;
}