package com.semillerojava.gestionproductos.servicio;

import com.semillerojava.gestionproductos.dto.CrearProductoDto;
import com.semillerojava.gestionproductos.dto.ProductoDto;
import com.semillerojava.gestionproductos.mapper.ProductoMapper;
import com.semillerojava.gestionproductos.modelo.Categoria;
import com.semillerojava.gestionproductos.modelo.Producto;
import com.semillerojava.gestionproductos.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServicio {

    private ProductoRepositorio productoRepositorio;
    private ProductoMapper productoMapper;
    private CategoriaServicio categoriaServicio;

    public ProductoServicio(
            ProductoRepositorio productoRepositorio,
            ProductoMapper productoMapper,
            CategoriaServicio categoriaServicio
    ) {
        this.productoRepositorio = productoRepositorio;
        this.productoMapper = productoMapper;
        this.categoriaServicio = categoriaServicio;
    }

    public ProductoDto crearProducto(CrearProductoDto crearProductoDto){

        Producto producto = productoMapper.crearProductoDtoToProductoEntity(crearProductoDto);

        Optional<Categoria> categoria =
                categoriaServicio.buscarCategoriaEnBd(crearProductoDto.getCategoría());

        producto.setCategoria(categoria.get());

        return productoMapper.productoEntityToProductoDto(
                productoRepositorio.save(producto)
        );
    }
}
