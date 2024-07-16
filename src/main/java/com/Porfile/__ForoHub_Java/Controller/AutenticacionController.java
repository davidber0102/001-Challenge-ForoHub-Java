package com.Porfile.__ForoHub_Java.Controller;

import com.Porfile.__ForoHub_Java.Domain.Usuario.RegistroUsuario;
import com.Porfile.__ForoHub_Java.Domain.Usuario.Usuario;
import com.Porfile.__ForoHub_Java.Infra.Security.JWTTokenDTO;
import com.Porfile.__ForoHub_Java.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity usuarioAutenticacion(@RequestBody @Valid RegistroUsuario usuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioDTO.nombre(),
                usuarioDTO.contrasenia());
        var autenticacionUsuario = authenticationManager.authenticate(authToken);
        var token= tokenService.generarToken((Usuario) autenticacionUsuario.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(token));
    }
}
