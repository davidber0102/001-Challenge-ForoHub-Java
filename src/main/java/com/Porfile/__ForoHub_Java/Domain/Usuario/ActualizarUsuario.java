package com.Porfile.__ForoHub_Java.Domain.Usuario;

import com.Porfile.__ForoHub_Java.Domain.Perfil.Perfil;
import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(
        @NotNull Long id,
        String nombre,
        String email){
}
