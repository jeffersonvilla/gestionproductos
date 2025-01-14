package com.semillerojava.gestionproductos.seguridad;

import com.semillerojava.gestionproductos.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private final String claveSecreta = "7fcbae0ef41e10b5a72e8c6f99f80c6bf73d90d0a531ed3fd69cefc8ef39a35c7146b798ba545864d1bfe5d523b0da77c3f022d6870217c5beecf601bc326a478a98a6dd9eb64b27dc83c39f8b889e88a6efbdc57e6836fecb1718a46ef1d03f972352fbafc5081b53e427cbde5dc0fbb430d2054c1bce3590bdfcdfc7e2ed990bac84a94055e6f29e810601c067bc06416eea6022855300bcce5cba6244ba480470703f39db9a49a3e3c798d60fc5a13ecf0f2371231efa3acaea193fa4c408b6b194546681b1c8f3e65929f920a62e77df43f521a74dc009a623ba22b481303a71907989cceb200c15e319195bd4c792e7f7098f1ffb7f076e06c52e10869a";

    public String generarToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROL_USUARIO_CLAIM", usuario.getRol().getNombre());
        return crearToken(claims, usuario.getUsername());
    }

    private String crearToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15 )) // 15 minutos
                .signWith(obtenerClaveSecreta())
                .compact();
    }

    private Key obtenerClaveSecreta(){
        byte[] keyBytes = Decoders.BASE64.decode(claveSecreta);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean tokenValido(String token, String username) {
        final String usernameExtraido = extraerUsername(token);
        return (usernameExtraido.equals(username) && !tokenExpirado(token));
    }

    public String extraerUsername(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    private Boolean tokenExpirado(String token) {
        return extraerExpiracion(token).before(new Date());
    }

    public Date extraerExpiracion(String token) {
        return extraerClaim(token, Claims::getExpiration);
    }

    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extraerTodasLasClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extraerTodasLasClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) obtenerClaveSecreta())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
