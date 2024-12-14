package com.semillerojava.gestionproductos.repositorio;

import com.semillerojava.gestionproductos.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
