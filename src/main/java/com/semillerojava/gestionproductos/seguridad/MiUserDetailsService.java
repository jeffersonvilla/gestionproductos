package com.semillerojava.gestionproductos.seguridad;

import com.semillerojava.gestionproductos.excepciones.UsuarioNoEncontradoException;
import com.semillerojava.gestionproductos.modelo.Usuario;
import com.semillerojava.gestionproductos.repositorio.UsuarioRepositorio;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MiUserDetailsService  implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    public MiUserDetailsService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsuarioNoEncontradoException {

        Optional<Usuario> usuarioEncontrado = usuarioRepositorio.findByUsername(username);

        if(usuarioEncontrado.isEmpty())
            throw new UsuarioNoEncontradoException("USUARIO " + username + " NO ENCONTRADO");

        return usuarioEncontrado.get();
    }

}
