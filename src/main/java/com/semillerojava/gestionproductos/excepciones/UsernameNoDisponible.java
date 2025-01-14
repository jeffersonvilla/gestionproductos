package com.semillerojava.gestionproductos.excepciones;

public class UsernameNoDisponible extends RuntimeException {
    public UsernameNoDisponible(String message) {
        super(message);
    }
}
