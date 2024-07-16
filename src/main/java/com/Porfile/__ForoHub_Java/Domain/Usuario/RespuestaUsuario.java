package com.Porfile.__ForoHub_Java.Domain.Usuario;

import com.Porfile.__ForoHub_Java.Domain.Perfil.Perfil;

public record RespuestaUsuario(
        Long id,
        String nombre) {

    public RespuestaUsuario(Perfil perfil){
        this(
                perfil.getId(),
                perfil.getNombre()
                      );
    }
}
