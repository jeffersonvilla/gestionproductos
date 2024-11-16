package com.semillerojava.gestionproductos.servicio;

import com.semillerojava.gestionproductos.dto.CategoriaDto;
import com.semillerojava.gestionproductos.mapper.CategoriaMapper;
import com.semillerojava.gestionproductos.repositorio.CategoriaRepositorio;
import org.springframework.stereotype.Service;

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

}
