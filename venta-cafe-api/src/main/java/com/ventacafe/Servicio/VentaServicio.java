package com.ventacafe.servicio;

import com.ventacafe.dto.VentaDetalleDTO;
import com.ventacafe.dto.VentaRequestDTO;
import com.ventacafe.dto.VentaResponseDTO;
import com.ventacafe.entidad.Venta;
import com.ventacafe.entidad.VentaDetalle;
import com.ventacafe.entidad.Cliente;
import com.ventacafe.repositorio.CafeRepositorio;
import com.ventacafe.repositorio.ClienteRepositorio;
import com.ventacafe.repositorio.VentaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VentaServicio {

    @Inject
    VentaRepositorio ventaRepositorio;

    @Inject
    ClienteRepositorio clienteRepositorio;

    @Inject
    AuditoriaServicio auditoriaServicio;

    /**
     * Calcula el total de una venta sin registrarla
     */
    public double calcularTotal(VentaRequestDTO request) {
        return request.getItems()
                .stream()
                .mapToDouble(i -> i.getCantidad() * i.getPrecioUnitario())
                .sum();
    }

    /**
     * Registra una venta en BD (JPA)
     */
    @Transactional
    public VentaResponseDTO registrarVenta(VentaRequestDTO request, String usuario) {

        Cliente cliente = clienteRepositorio.findById(request.getIdCliente());
        if (cliente == null) {
            throw new WebApplicationException("Cliente no encontrado: " + request.getIdCliente(), 404);
        }

        Venta venta = new Venta();
        venta.cliente = cliente;
        venta.fecha = LocalDate.now();

        double total = 0.0;

        for (VentaDetalleDTO item : request.getItems()) {
            double subtotal = item.getCantidad() * item.getPrecioUnitario();
            total += subtotal;

            VentaDetalle det = new VentaDetalle();
            det.producto = item.getProducto();
            det.cantidad = item.getCantidad();
            det.precioUnitario = item.getPrecioUnitario();
            det.subtotal = subtotal;

            venta.addDetalle(det);
        }

        venta.montoTotal = total;
        venta.registrarCreacion(usuario);

        ventaRepositorio.persist(venta);

        // Auditoría
        String detalle = String.format("Venta creada: Cliente=%s, Total=%.2f", cliente.id_cliente, total);
        auditoriaServicio.registrar("Venta", "CREACION", detalle, usuario);

        return mapToResponse(venta);
    }

    public List<VentaResponseDTO> listarVentas() {
        return ventaRepositorio.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public VentaResponseDTO obtenerPorId(Long id) {
        Venta venta = ventaRepositorio.findById(id);
        if (venta == null) {
            throw new WebApplicationException("Venta no encontrada: " + id, 404);
        }
        return mapToResponse(venta);
    }

    private VentaResponseDTO mapToResponse(Venta venta) {
        VentaResponseDTO dto = new VentaResponseDTO();
        dto.setId(venta.id);
        dto.setCliente(venta.cliente != null ? venta.cliente.nombre : null);
        dto.setTotal(venta.montoTotal != null ? venta.montoTotal : 0.0);
        dto.setFecha(venta.fecha);

        List<com.ventacafe.dto.VentaDetalleDTO> items = venta.detalles
                .stream()
                .map(d -> {
                    com.ventacafe.dto.VentaDetalleDTO it = new com.ventacafe.dto.VentaDetalleDTO();
                    it.setProducto(d.producto);
                    it.setCantidad(d.cantidad);
                    it.setPrecioUnitario(d.precioUnitario);
                    return it;
                })
                .collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }
}
