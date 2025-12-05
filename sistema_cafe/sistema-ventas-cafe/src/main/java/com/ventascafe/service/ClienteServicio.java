package com.ventascafe.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ventascafe.entity.Cliente;
import com.ventascafe.repository.ClienteRepositorio;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@ApplicationScoped
@Service
public class ClienteServicio {
    
    @Inject
    ClienteRepositorio clienteRepositorio;
    
    public List<Cliente> obtenerTodos() {
        return clienteRepositorio.listAll();
    }
    
    public Cliente obtenerPorId(Long id) {
        return clienteRepositorio.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id: " + id));
    }
    
    @Transactional
    public Cliente crear(Cliente cliente) {
        if (ClienteRepositorio.existePorEmail(cliente.email)) {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + cliente.email);
        }
        ((PanacheEntityBase) clienteRepositorio).persist(cliente);
        return cliente;
    }
    
    @Transactional
    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente clienteExistente = obtenerPorId(id);
        
        if (!clienteExistente.email.equals(cliente.email) && 
            ClienteRepositorio.existePorEmail(cliente.email)) {
            throw new IllegalArgumentException("El email " + cliente.email + " ya está en uso");
        }
        
        clienteExistente.nombre = cliente.nombre;
        clienteExistente.apellido = cliente.apellido;
        clienteExistente.email = cliente.email;
        clienteExistente.telefono = cliente.telefono;
        clienteExistente.direccion = cliente.direccion;
        
        return clienteExistente;
    }
    
  
}