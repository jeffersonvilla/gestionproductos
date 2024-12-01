package com.semillerojava.gestionproductos.servicio;

import com.semillerojava.gestionproductos.dto.CategoriaDto;
import com.semillerojava.gestionproductos.excepciones.CategoriaNoEncontradaException;
import com.semillerojava.gestionproductos.mapper.CategoriaMapper;
import com.semillerojava.gestionproductos.modelo.Categoria;
import com.semillerojava.gestionproductos.repositorio.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicio {

    private CategoriaRepositorio categoriaRepositorio;
    private CategoriaMapper categoriaMapper;

    public CategoriaServicio(CategoriaRepositorio categoriaRepositorio,
                             CategoriaMapper categoriaMapper) {
        this.categoriaRepositorio = categoriaRepositorio;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaDto crearCategoria(CategoriaDto categoriaDto){
        return categoriaMapper.categoriaEntityToCategoriaDto(
                categoriaRepositorio.save(
                        categoriaMapper.categoriaDtoToCategoriaEntity(
                                categoriaDto
                        )
                )
        );
    }

    public List<CategoriaDto> obtenerCategorias(){
        return categoriaRepositorio.findAll()
                .stream()
                .map(categoriaMapper::categoriaEntityToCategoriaDto)
                .toList();
    }

    public CategoriaDto obtenerCategoriaPorId(Long id){

        Optional<Categoria> categoria = categoriaRepositorio.findById(id);

        if(categoria.isEmpty()){
            throw new CategoriaNoEncontradaException(
                    "No se encuentra la categoria con id: "+ id
            );
        }

        return categoria.map(categoriaMapper::categoriaEntityToCategoriaDto).get();

    }

}
