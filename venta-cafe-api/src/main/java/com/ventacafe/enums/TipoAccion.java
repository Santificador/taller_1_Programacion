package com.ventacafe.enums;

/**
 * Enum que representa los tipos de acciones de auditoría
 * que se pueden realizar sobre las entidades del sistema.
 */
public enum TipoAccion {
    /**
     * Acción de creación de un nuevo registro
     */
    CREACION("Creación"),
    
    /**
     * Acción de modificación de un registro existente
     */
    MODIFICACION("Modificación"),
    
    /**
     * Acción de eliminación de un registro
     */
    ELIMINACION("Eliminación");

    private final String descripcion;

    TipoAccion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
