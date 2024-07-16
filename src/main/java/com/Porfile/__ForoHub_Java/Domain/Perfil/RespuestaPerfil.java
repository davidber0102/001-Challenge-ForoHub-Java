package com.Porfile.__ForoHub_Java.Domain.Perfil;

public record RespuestaPerfil(
        Long id,
        String nombre) {
    public RespuestaPerfil(Perfil perfil) {
        this(
                perfil.getId(),
                perfil.getNombre() );
    }
}
