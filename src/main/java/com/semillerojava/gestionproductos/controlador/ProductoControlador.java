package com.semillerojava.gestionproductos.controlador;

import com.semillerojava.gestionproductos.dto.CrearProductoDto;
import com.semillerojava.gestionproductos.dto.ProductoDto;
import com.semillerojava.gestionproductos.servicio.ProductoServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("producto")
public class ProductoControlador {

    private ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PostMapping("crear")
    public ResponseEntity<ProductoDto> crearProducto(
            @RequestBody @Valid CrearProductoDto crearProductoDto
    ){

        return new ResponseEntity<>(
                productoServicio.crearProducto(crearProductoDto),
                HttpStatus.CREATED
        );
    }
}
