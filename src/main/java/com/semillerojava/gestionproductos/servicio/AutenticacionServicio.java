package com.semillerojava.gestionproductos.servicio;

import com.semillerojava.gestionproductos.modelo.Usuario;
import com.semillerojava.gestionproductos.repositorio.UsuarioRepositorio;
import com.semillerojava.gestionproductos.seguridad.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AutenticacionServicio(UsuarioRepositorio usuarioRepositorio,
                                 BCryptPasswordEncoder passwordEncoder,
                                 JwtUtil jwtUtil) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String autenticar(String username, String clave){

        Usuario usuario = usuarioRepositorio.findByUsername(username).get();

        if(passwordEncoder.matches(clave, usuario.getClave())){
            return jwtUtil.generarToken(usuario);
        }

        return "";
    }
}
