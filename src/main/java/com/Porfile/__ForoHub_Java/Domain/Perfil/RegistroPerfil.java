package com.Porfile.__ForoHub_Java.Domain.Perfil;

import jakarta.validation.constraints.NotNull;

public record RegistroPerfil(
        @NotNull(message = "Nombre de Curso al que pertenece Topico/Respuesta")
        String nombre
) {
}
