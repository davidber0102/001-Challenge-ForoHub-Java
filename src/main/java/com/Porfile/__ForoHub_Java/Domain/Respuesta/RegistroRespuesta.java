package com.Porfile.__ForoHub_Java.Domain.Respuesta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RegistroRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        @Valid
        Long usuario_id,
        @NotNull
        @Valid
        Long topico_id,
        @NotNull
        @Future
        LocalDateTime fechaCreacion) {
}
