package com.ventacafe.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class VentaRequestDTO {

    @NotNull
    private Long idCliente;

    @NotEmpty
    private List<VentaDetalleDTO> items;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public List<VentaDetalleDTO> getItems() {
        return items;
    }

    public void setItems(List<VentaDetalleDTO> items) {
        this.items = items;
    }
}
