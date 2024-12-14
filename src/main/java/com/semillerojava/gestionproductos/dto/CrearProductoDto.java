package com.semillerojava.gestionproductos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearProductoDto {

    @NotBlank
    private String nombre;

    @NotNull
    @Min(value = 0)
    private double precio;

    @NotNull
    private Long categoría;

    public CrearProductoDto(String nombre, double precio, Long categoría) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoría = categoría;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    @NotNull
    @Min(value = 0)
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull @Min(value = 0) double precio) {
        this.precio = precio;
    }

    public @NotNull Long getCategoría() {
        return categoría;
    }

    public void setCategoría(@NotNull Long categoría) {
        this.categoría = categoría;
    }
}
