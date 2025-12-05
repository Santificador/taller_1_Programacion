package com.ventascafe.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventascafe.entity.Cliente;

@ApplicationScoped

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
 
    
    public static boolean existePorEmail(String email) {
        return count("email", email) > 0;
    }

    public static int count(String string, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    public List<Cliente> listAll();

   
}
// public class ClienteRepositorio implements PanacheRepository<Cliente> {
    
//     public Optional<Cliente> buscarPorEmail(String email) {
//         return find("email", email).firstResultOptional();
//     }
    
//     public boolean existePorEmail(String email) {
//         return count("email", email) > 0;
//     }
// }