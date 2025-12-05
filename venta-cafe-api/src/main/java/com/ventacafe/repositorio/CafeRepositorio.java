package com.ventacafe.repositorio;

import com.ventacafe.entidad.Cafe;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CafeRepositorio implements PanacheRepository<Cafe> {
}
