package com.semillerojava.gestionproductos.controlador;

import com.semillerojava.gestionproductos.dto.ActualizarProductoDto;
import com.semillerojava.gestionproductos.dto.CrearProductoDto;
import com.semillerojava.gestionproductos.dto.ProductoDto;
import com.semillerojava.gestionproductos.servicio.ProductoServicio;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("listar")
    public ResponseEntity<List<ProductoDto>> obtenerProductos(){
        return new ResponseEntity<>(
                productoServicio.obtenerProductos(),
                HttpStatus.OK
        );
    }

    @GetMapping("obtener")
    public ResponseEntity<ProductoDto> obtenerProductoPorId(@RequestParam Long id){

        return new ResponseEntity<>(
                productoServicio.obtenerProductoPorId(id),
                HttpStatus.OK
        );
    }

    @PutMapping("actualizar")
    public ResponseEntity<ProductoDto> actualizarProducto(
            @RequestParam Long id, @RequestBody @Valid ActualizarProductoDto dto
    ){

        return new ResponseEntity<>(
                productoServicio.actualizarProducto(id, dto),
                HttpStatus.OK
        );
    }
}
