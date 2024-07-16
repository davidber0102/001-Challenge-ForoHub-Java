package com.Porfile.__ForoHub_Java.Domain.Usuario;

import com.Porfile.__ForoHub_Java.Infra.Errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registarUsuario(RegistroUsuario registroUsuario){
        if(usuarioRepository.existsByEmail(registroUsuario.email())){
            throw new ValidacionDeIntegridad("Correo electrónico está registrado.");
        }
        if(usuarioRepository.existsByNombre(registroUsuario.nombre())){
            throw new ValidacionDeIntegridad("Nombre de usuario esta en uso, Verifique.");
        }
        var usuario = new Usuario(registroUsuario, passwordEncoder);
        usuarioRepository.save(usuario);
    }

    public void actualizarUsuario(Long id, ActualizarUsuario actualizarUs){
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("Usuario con el ID proporcionado NO existe.");
        }
        var usuario = usuarioOptional.get();
        usuario.ActualizarUsuario(actualizarUs);
        usuarioRepository.save(usuario);
    }

    public void desActivarUsuario(Long id){
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("Usuario con el ID proporcionado no existe.");
        }
        var usuario = usuarioOptional.get();
        usuario.desActivarUsuario();
        usuarioRepository.save(usuario);
    }
}
