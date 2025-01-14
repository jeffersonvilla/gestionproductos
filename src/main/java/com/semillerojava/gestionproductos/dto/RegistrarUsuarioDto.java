package com.semillerojava.gestionproductos.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistrarUsuarioDto {

    @NotBlank
    private String username;

    @NotBlank
    private String clave;

    public RegistrarUsuarioDto(String username, String clave) {
        this.username = username;
        this.clave = clave;
    }

    public RegistrarUsuarioDto() {
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public @NotBlank String getClave() {
        return clave;
    }
}
