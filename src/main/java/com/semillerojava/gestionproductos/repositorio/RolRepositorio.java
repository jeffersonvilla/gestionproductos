package com.semillerojava.gestionproductos.repositorio;

import com.semillerojava.gestionproductos.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepositorio extends JpaRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);
}
