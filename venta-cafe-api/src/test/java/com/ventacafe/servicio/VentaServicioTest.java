package com.ventacafe.servicio;

import com.ventacafe.dto.VentaDetalleDTO;
import com.ventacafe.dto.VentaRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class VentaServicioTest {

    @Test
    public void calcularTotal_variosItems_debeSumar() {
        VentaServicio servicio = new VentaServicio();

        VentaDetalleDTO i1 = new VentaDetalleDTO();
        i1.setProducto("Cafe A");
        i1.setCantidad(2);
        i1.setPrecioUnitario(3.5);

        VentaDetalleDTO i2 = new VentaDetalleDTO();
        i2.setProducto("Cafe B");
        i2.setCantidad(1);
        i2.setPrecioUnitario(5.0);

        VentaRequestDTO req = new VentaRequestDTO();
        req.setIdCliente(1L);
        req.setItems(List.of(i1, i2));

        double total = servicio.calcularTotal(req);

        Assertions.assertEquals(12.0, total, 0.0001);
    }
}
