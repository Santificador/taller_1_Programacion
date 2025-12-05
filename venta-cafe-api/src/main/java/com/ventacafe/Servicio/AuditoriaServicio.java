package com.ventacafe.servicio;

import com.ventacafe.entidad.Auditoria;
import com.ventacafe.enums.TipoAccion;
import com.ventacafe.repositorio.AuditoriaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

@ApplicationScoped
public class AuditoriaServicio {

    @Inject
    AuditoriaRepositorio repositorio;

    public void registrar(String entidad, TipoAccion operacion, String detalle, String usuario) {
        Auditoria auditoria = new Auditoria();
        auditoria.nombreEntidad = entidad;
        auditoria.accion = operacion;
        auditoria.detalles = detalle;
        auditoria.fechaHora = LocalDateTime.now();
        auditoria.usuario = usuario;

        repositorio.persist(auditoria);
    }
    
    // Método auxiliar para compatibilidad con código antiguo que usa String
    public void registrar(String entidad, String operacion, String detalle, String usuario) {
        TipoAccion tipoAccion;
        try {
            tipoAccion = TipoAccion.valueOf(operacion.toUpperCase());
        } catch (IllegalArgumentException e) {
            tipoAccion = TipoAccion.MODIFICACION; // valor por defecto
        }
        registrar(entidad, tipoAccion, detalle, usuario);
    }
}
