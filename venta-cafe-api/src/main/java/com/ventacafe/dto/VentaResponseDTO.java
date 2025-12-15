package com.ventacafe.dto;

import java.time.LocalDate;
import java.util.List;

public class VentaResponseDTO {

    private Long id;
    private String cliente;
    private double total;
    private LocalDate fecha;
    private List<VentaDetalleDTO> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<VentaDetalleDTO> getItems() {
        return items;
    }

    public void setItems(List<VentaDetalleDTO> items) {
        this.items = items;
    }
}
