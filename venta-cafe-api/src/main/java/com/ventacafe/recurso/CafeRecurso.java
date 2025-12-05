package com.ventacafe.recurso;

import com.ventacafe.entidad.Cafe;
import com.ventacafe.repositorio.CafeRepositorio;
import io.quarkus.panache.common.Page;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.List;

@Path("/api/cafes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CafeRecurso {

    @Inject
    CafeRepositorio repositorio;

    @GET
    public Response listar(@QueryParam("page") @DefaultValue("0") int page,
                           @QueryParam("size") @DefaultValue("10") int size) {
        List<Cafe> cafes = repositorio.findAll()
                                      .page(Page.of(page, size))
                                      .list();
        return Response.ok(cafes).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Cafe cafe = repositorio.findById(id);
        if (cafe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cafe).build();
    }

    @POST
    @Transactional
    public Response crear(Cafe cafe, @Context UriInfo uriInfo) {
        repositorio.persist(cafe);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(cafe.id_cafe));
        return Response.created(builder.build()).entity(cafe).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Long id, Cafe datos) {
        Cafe cafe = repositorio.findById(id);
        if (cafe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        cafe.nombre = datos.nombre;
        cafe.descripcion = datos.descripcion;
        cafe.precio = datos.precio;
        cafe.origen = datos.origen;
        cafe.tipoTostado = datos.tipoTostado;
        return Response.ok(cafe).build();
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
