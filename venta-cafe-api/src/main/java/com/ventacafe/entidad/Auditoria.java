package com.ventacafe.entidad;

import com.ventacafe.enums.TipoAccion;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "ENTIDAD", nullable = false)
    public String nombreEntidad;

    @Enumerated(EnumType.STRING)
    @Column(name = "OPERACION", nullable = false, length = 20)
    public TipoAccion accion;

    @Column(name = "DETALLE", length = 500)
    public String detalles;

    @Column(name = "FECHA", nullable = false)
    public LocalDateTime fechaHora;

    @Column(name = "USUARIO", nullable = false, length = 100)
    public String usuario;

    @Column(name = "ID_REGISTRO")
    public Long idRegistro;

    // ========== MÉTODOS AUXILIARES ==========

    /**
     * Crea un nuevo registro de auditoría
     */
    public static Auditoria crear(String nombreEntidad, TipoAccion accion, String usuario, Long idRegistro, String detalles) {
        Auditoria auditoria = new Auditoria();
        auditoria.nombreEntidad = nombreEntidad;
        auditoria.accion = accion;
        auditoria.usuario = usuario;
        auditoria.idRegistro = idRegistro;
        auditoria.detalles = detalles;
        auditoria.fechaHora = LocalDateTime.now();
        return auditoria;
    }
}
