package com.ventascafe.resource;

import com.ventascafe.entity.Cliente;
import com.ventascafe.service.ClienteServicio;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/clientes")


public class ClienteControlador {
    
      @Autowired
    private ClienteServicio clienteServicio;

    
    @GetMapping
    public List<Cliente> obtenerTodos() {
        return clienteServicio.obtenerTodos();
    }
    
    @GET
    @Path("/{id}")
    public Cliente obtenerPorId(@PathParam("id") Long id) {
        return clienteServicio.obtenerPorId(id);
    }
    
  
    
    @PUT
    @Path("/{id}")
    public Cliente actualizar(@PathParam("id") Long id, @Valid Cliente cliente) {
        return clienteServicio.actualizar(id, cliente);
    }
    
   
}