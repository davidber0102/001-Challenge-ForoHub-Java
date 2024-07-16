package com.Porfile.__ForoHub_Java.Domain.Perfil;

public record ListarPerfil(
        Long id,
        String nombre) {

    public ListarPerfil(Perfil perfil) {
        this(
               perfil.getId(),
               perfil.getNombre() );
    }
}
