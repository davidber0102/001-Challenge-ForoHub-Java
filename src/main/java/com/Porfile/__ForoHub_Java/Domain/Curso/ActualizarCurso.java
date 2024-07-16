package com.Porfile.__ForoHub_Java.Domain.Curso;

import jakarta.validation.constraints.NotNull;

public record ActualizarCurso(
        @NotNull
        Long id,
        String nombre,
        String categoria) {
}
