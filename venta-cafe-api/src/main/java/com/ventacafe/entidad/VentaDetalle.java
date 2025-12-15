package com.ventacafe.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "venta_detalles")
public class VentaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "PRODUCTO")
    public String producto;

    @Column(name = "CANTIDAD")
    public Integer cantidad;

    @Column(name = "PRECIO_UNITARIO")
    public Double precioUnitario;

    @Column(name = "SUBTOTAL")
    public Double subtotal;

    @ManyToOne
    @JoinColumn(name = "VENTA_ID")
    public Venta venta;
}
