package com.ventacafe.recurso;

import com.ventacafe.entidad.Auditoria;
import com.ventacafe.repositorio.AuditoriaRepositorio;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/api/auditorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditoriaRecurso {

    @Inject
    AuditoriaRepositorio repositorio;

    @GET
    public Response listar(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size) {
        List<Auditoria> auditorias = repositorio.findAll()
                                                .page(Page.of(page, size))
                                                .list();
        return Response.ok(auditorias).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Auditoria auditoria = repositorio.findById(id);
        if (auditoria == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(auditoria).build();
    }

    @POST
    @Transactional
    public Response crear(Auditoria auditoria, @Context UriInfo uriInfo) {
        repositorio.persist(auditoria);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(auditoria.id));
        return Response.created(builder.build()).entity(auditoria).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Long id, Auditoria datos) {
        Auditoria auditoria = repositorio.findById(id);
        if (auditoria == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        auditoria.nombreEntidad = datos.nombreEntidad;
        auditoria.accion = datos.accion;
        auditoria.detalles = datos.detalles;
        auditoria.fechaHora = datos.fechaHora;
        auditoria.usuario = datos.usuario;
        return Response.ok(auditoria).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Long id) {
        boolean eliminado = repositorio.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
