package com.semillerojava.gestionproductos.repositorio;

import com.semillerojava.gestionproductos.modelo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
