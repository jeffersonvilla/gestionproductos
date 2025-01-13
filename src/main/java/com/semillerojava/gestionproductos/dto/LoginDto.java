package com.semillerojava.gestionproductos.dto;

public class LoginDto {

    private String username;
    private String clave;

    public LoginDto(String username, String clave) {
        this.username = username;
        this.clave = clave;
    }

    public String getUsername() {
        return username;
    }

    public String getClave() {
        return clave;
    }
}
