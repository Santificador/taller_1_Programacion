package com.ventacafe.recurso;

import com.ventacafe.entidad.Auditoria;
import com.ventacafe.entidad.Venta;
import com.ventacafe.enums.TipoAccion;
import com.ventacafe.repositorio.AuditoriaRepositorio;
import com.ventacafe.repositorio.VentaRepositorio;
import com.ventacafe.servicio.VentaServicio;
import com.ventacafe.dto.VentaRequestDTO;
import com.ventacafe.dto.VentaResponseDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import jakarta.validation.Valid;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/api/ventas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VentaRecurso {

    @Inject
    VentaRepositorio repositorio;

    @Inject
    VentaServicio ventaServicio;

    @Inject
    AuditoriaRepositorio auditoriaRepositorio;

    @GET
    public Response listar(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size) {
        List<VentaResponseDTO> ventas = ventaServicio.listarVentas();
        return Response.ok(ventas).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        VentaResponseDTO dto = ventaServicio.obtenerPorId(id);
        return Response.ok(dto).build();
    }

    @POST
    @Transactional
    @Operation(summary = "Registrar venta")
    @APIResponse(responseCode = "201", description = "Venta creada")
    public Response crear(@Valid VentaRequestDTO request, 
                          @Context UriInfo uriInfo,
                          @HeaderParam("X-Usuario") @DefaultValue("sistema") String usuario) {
        VentaResponseDTO creado = ventaServicio.registrarVenta(request, usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(creado.getId()));
        return Response.created(builder.build()).entity(creado).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Long id, 
                               Venta datos,
                               @HeaderParam("X-Usuario") @DefaultValue("sistema") String usuario) {
        Venta venta = repositorio.findById(id);
        if (venta == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Guardar valores antiguos para auditoría
        String valoresAntiguos = String.format("Cantidad:%d, Total:%.2f", 
                venta.cantidad, venta.montoTotal);
        
        // Actualizar datos
        venta.cliente = datos.cliente;
        venta.cafe = datos.cafe;
        venta.cantidad = datos.cantidad;
        venta.precioUnitario = datos.precioUnitario;
        venta.montoTotal = datos.montoTotal;
        venta.fecha = datos.fecha;
        
        // Registrar auditoría de modificación
        venta.registrarModificacion(usuario);
        
        // Crear registro de auditoría
        String detalle = String.format("Venta modificada. Valores anteriores: %s. Nuevos valores: Cantidad=%d, Total=%.2f",
                valoresAntiguos, venta.cantidad, venta.montoTotal);
        
        Auditoria auditoria = Auditoria.crear("Venta", TipoAccion.MODIFICACION, usuario, venta.id, detalle);
        auditoriaRepositorio.persist(auditoria);
        
        return Response.ok(venta).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id,
                             @HeaderParam("X-Usuario") @DefaultValue("sistema") String usuario) {
        Venta venta = repositorio.findById(id);
        if (venta == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Guardar información antes de eliminar
        String detalle = String.format("Venta eliminada: ID=%d, Cliente=%s, Total=%.2f",
                venta.id,
                venta.cliente != null ? venta.cliente.id_cliente : "N/A",
                venta.montoTotal);
        
        // Crear registro de auditoría antes de eliminar
        Auditoria auditoria = Auditoria.crear("Venta", TipoAccion.ELIMINACION, usuario, id, detalle);
        auditoriaRepositorio.persist(auditoria);
        
        // Eliminar la venta
        repositorio.deleteById(id);
        
        return Response.noContent().build();
    }
}
