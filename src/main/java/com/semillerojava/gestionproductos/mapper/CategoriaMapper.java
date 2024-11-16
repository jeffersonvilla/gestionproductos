package com.semillerojava.gestionproductos.mapper;

import com.semillerojava.gestionproductos.dto.CategoriaDto;
import com.semillerojava.gestionproductos.modelo.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", ignore = true)
    Categoria categoriaDtoToCategoriaEntity(CategoriaDto categoriaDto);

    CategoriaDto categoriaEntityToCategoriaDto(Categoria categoriaEntity);
}
