package com.Porfile.__ForoHub_Java.Domain.Perfil;

import jakarta.validation.constraints.NotNull;

public record ActualizarPerfil(
        @NotNull
        Long id,
        String nombre) {
}
