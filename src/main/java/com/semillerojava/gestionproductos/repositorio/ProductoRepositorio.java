package com.semillerojava.gestionproductos.repositorio;

import com.semillerojava.gestionproductos.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoriaIdIn(List<Long> ids);
}
