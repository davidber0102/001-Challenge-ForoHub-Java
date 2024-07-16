package com.Porfile.__ForoHub_Java.Domain.Respuesta;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ActualizacionRespuesta(
        @NotNull
        Long id,
        String mensaje,
        @NotNull
        Long topico_id,
        LocalDateTime fechaCreacion,
        @NotNull
        Long autor_id,
        Boolean solucion) {
}
