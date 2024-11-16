package com.semillerojava.gestionproductos.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDto {

    private Long id;

    @NotBlank
    private String nombre;

    public CategoriaDto() {
    }

    public CategoriaDto(String nombre, Long id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
