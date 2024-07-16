package com.Porfile.__ForoHub_Java.Domain.Usuario;

public record ListarUsuarios(
        Long id,
        String nombre,
        String email,
        Long perfil_id) {

    public ListarUsuarios(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPerfil().getId()        );
    }
}
