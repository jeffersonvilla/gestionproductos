package com.semillerojava.gestionproductos.controlador;

import com.semillerojava.gestionproductos.dto.RegistrarUsuarioDto;
import com.semillerojava.gestionproductos.servicio.RegistroServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registro")
public class RegistroControlador {

    private final RegistroServicio registroServicio;

    public RegistroControlador(RegistroServicio registroServicio) {
        this.registroServicio = registroServicio;
    }

    @PostMapping("empleado")
    public ResponseEntity registrarUsuarioEmpleado(@RequestBody @Valid RegistrarUsuarioDto dto){

        registroServicio.registrarUsuario(dto);
        return ResponseEntity.ok().build();
    }
}
