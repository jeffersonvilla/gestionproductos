package com.semillerojava.gestionproductos.servicio;

import com.semillerojava.gestionproductos.dto.RegistrarUsuarioDto;
import com.semillerojava.gestionproductos.excepciones.UsernameNoDisponible;
import com.semillerojava.gestionproductos.modelo.Rol;
import com.semillerojava.gestionproductos.modelo.Usuario;
import com.semillerojava.gestionproductos.repositorio.RolRepositorio;
import com.semillerojava.gestionproductos.repositorio.UsuarioRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final RolRepositorio rolRepositorio;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistroServicio(UsuarioRepositorio usuarioRepositorio,
                            RolRepositorio rolRepositorio,
                            BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrarUsuario(RegistrarUsuarioDto dto){

        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByUsername(dto.getUsername());

        if(usuarioEncontrado.isPresent()){
            throw new UsernameNoDisponible("El username " + dto.getUsername() + " ya está en uso");
        }

        Rol rolEmpleado = rolRepositorio.findByNombre("EMPLEADO").get();

        Usuario usuarioNuevo = new Usuario(
                dto.getUsername(),
                passwordEncoder.encode(dto.getClave()),
                rolEmpleado);

        usuarioRepositorio.save(usuarioNuevo);

    }

}
