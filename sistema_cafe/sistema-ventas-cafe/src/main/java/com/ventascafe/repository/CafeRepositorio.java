package com.ventascafe.repository;

import com.ventascafe.entity.Cafe;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CafeRepositorio implements PanacheRepository<Cafe> {
    
    public Cafe buscarPorNombre(String nombre) {
        return find("nombre", nombre).firstResult();
    }
}