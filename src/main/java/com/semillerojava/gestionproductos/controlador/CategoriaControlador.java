package com.semillerojava.gestionproductos.controlador;

import com.semillerojava.gestionproductos.dto.CategoriaDto;
import com.semillerojava.gestionproductos.servicio.CategoriaServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoria")
public class CategoriaControlador {

    private CategoriaServicio categoriaServicio;

    public CategoriaControlador(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @PostMapping("crear")
    public ResponseEntity<CategoriaDto> crearCategoria(@RequestBody @Valid CategoriaDto categoriaDto){
        return new ResponseEntity<>(
                categoriaServicio.crearCategoria(categoriaDto),
                HttpStatus.CREATED
        );
    }
}
