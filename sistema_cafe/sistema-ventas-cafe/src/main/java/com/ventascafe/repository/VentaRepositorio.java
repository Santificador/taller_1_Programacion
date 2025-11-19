package com.ventascafe.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

import com.ventascafe.entity.Venta;

@ApplicationScoped
public class VentaRepositorio implements PanacheRepository<Venta> {
    
    public List<Venta> buscarPorClienteId(Long clienteId) {
        return list("cliente.id", clienteId);
    }
    
    public List<Venta> buscarPorCafeId(Long cafeId) {
        return list("cafe.id", cafeId);
    }
    
    public List<Venta> buscarPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return list("fechaVenta >= ?1 and fechaVenta <= ?2", fechaInicio, fechaFin);
    }
    
    public List<Venta> buscarPorRangoFechasYClienteId(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId) {
        return list("fechaVenta >= ?1 and fechaVenta <= ?2 and cliente.id = ?3", 
                   fechaInicio, fechaFin, clienteId);
    }
}