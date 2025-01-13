package com.semillerojava.gestionproductos.controlador;

import com.semillerojava.gestionproductos.dto.LoginDto;
import com.semillerojava.gestionproductos.servicio.AutenticacionServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("autenticacion")
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;

    public AutenticacionControlador(AutenticacionServicio autenticacionServicio) {
        this.autenticacionServicio = autenticacionServicio;
    }

    @GetMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        return ResponseEntity.ok(
                autenticacionServicio.autenticar(
                        loginDto.getUsername(), loginDto.getClave()
                )
        );
    }
}
