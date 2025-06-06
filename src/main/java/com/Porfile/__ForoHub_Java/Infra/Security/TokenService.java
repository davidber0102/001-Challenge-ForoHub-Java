package com.Porfile.__ForoHub_Java.Infra.Security;


import com.Porfile.__ForoHub_Java.Domain.Usuario.Usuario;
import com.Porfile.__ForoHub_Java.Domain.Usuario.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    private Usuario usuario;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generarToken (Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(usuario.getPassword());
            return JWT.create()
                    .withIssuer("foro_hub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
    public String getSubject(String token){
        if (token == null) {
            throw new IllegalArgumentException("El token es nulo.");
        }
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            if (username == null) {
                throw new IllegalArgumentException("Token no válido: Asunto no encontrado");
            }
            Usuario usuario = (Usuario) usuarioRepository.findByNombre(username);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado: " + username);
            }

            Algorithm algorithm = Algorithm.HMAC256(usuario.getPassword());
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("foro_hub")
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Token no válido: " + e.getMessage(), e);
        }
    }
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-06:00"));
    }
}
