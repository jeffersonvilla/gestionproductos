package com.semillerojava.gestionproductos.repositorio;

import com.semillerojava.gestionproductos.modelo.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Long> {
}
