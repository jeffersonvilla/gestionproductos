package com.semillerojava.gestionproductos.servicio;

import com.semillerojava.gestionproductos.dto.ActualizarProductoDto;
import com.semillerojava.gestionproductos.dto.CrearProductoDto;
import com.semillerojava.gestionproductos.dto.ProductoDto;
import com.semillerojava.gestionproductos.excepciones.ProductoNoEncontradoException;
import com.semillerojava.gestionproductos.mapper.ProductoMapper;
import com.semillerojava.gestionproductos.modelo.Categoria;
import com.semillerojava.gestionproductos.modelo.Producto;
import com.semillerojava.gestionproductos.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<ProductoDto> obtenerProductos(){

        return productoRepositorio.findAll().
                stream().
                map(productoMapper::productoEntityToProductoDto).
                toList();
    }

    public ProductoDto obtenerProductoPorId(Long id){

        Optional<Producto> producto = buscarProductoEnBd(id);

        return producto.map(productoMapper::productoEntityToProductoDto).get();
    }

    public ProductoDto actualizarProducto(Long id, ActualizarProductoDto dto){

        Producto producto = buscarProductoEnBd(id).get();

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());

        return productoMapper.productoEntityToProductoDto(
                productoRepositorio.save(producto)
        );
    }

    public void eliminarProducto(Long id){

        buscarProductoEnBd(id);

        productoRepositorio.deleteById(id);
    }

    public Optional<Producto> buscarProductoEnBd(Long id){

        Optional<Producto> producto = productoRepositorio.findById(id);

        if(producto.isEmpty()){
            throw new ProductoNoEncontradoException(
                    "No se encuentra producto con id: "+ id);
        }

        return producto;
    }

    public List<ProductoDto> filtrarPorNombre(String nombre){

        return productoRepositorio.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(productoMapper::productoEntityToProductoDto)
                .toList();
    }

    public List<ProductoDto> filtrarPorCategoria(List<Long> ids){

        return productoRepositorio.findByCategoriaIdIn(ids)
                .stream()
                .map(productoMapper::productoEntityToProductoDto)
                .toList();
    }



}
