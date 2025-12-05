package com.ventacafe.entidad;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    public Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_CAFE")
    public Cafe cafe;

    @Column(name = "CANTIDAD_VENTAS")
    public Integer cantidad;

    @Column(name = "PRECIO_UNITARIO_VENTAS")
    public Double precioUnitario;

    @Column(name = "MONTO_TOTAL_VENTAS")
    public Double montoTotal;

    @Column(name = "FECHA_VENTAS")
    public LocalDate fecha;

    // ========== CAMPOS DE AUDITORÍA ==========
    
    /**
     * Usuario que creó el registro
     */
    @Column(name = "CREADO_POR", length = 100)
    public String creadoPor;

    /**
     * Fecha y hora de creación del registro
     */
    @Column(name = "FECHA_CREACION")
    public LocalDateTime fechaCreacion;

    /**
     * Usuario que realizó la última modificación
     */
    @Column(name = "MODIFICADO_POR", length = 100)
    public String modificadoPor;

    /**
     * Fecha y hora de la última modificación
     */
    @Column(name = "FECHA_MODIFICACION")
    public LocalDateTime fechaModificacion;

    // ========== MÉTODOS AUXILIARES DE AUDITORÍA ==========

    /**
     * Establece la información de auditoría para creación
     * @param usuario Usuario que crea el registro
     */
    public void registrarCreacion(String usuario) {
        this.creadoPor = usuario;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Establece la información de auditoría para modificación
     * @param usuario Usuario que modifica el registro
     */
    public void registrarModificacion(String usuario) {
        this.modificadoPor = usuario;
        this.fechaModificacion = LocalDateTime.now();
    }
}
