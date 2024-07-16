package com.Porfile.__ForoHub_Java.Domain.Respuesta;

import java.time.LocalDateTime;

public record RespuestaCreada(
        Long id,
        String mensaje,
        Long topico_id,
        LocalDateTime fechaCreacion,
        Long usuario_id
        ) {

    public RespuestaCreada(Respuesta respuestaVerificada){
        this(
                respuestaVerificada.getId(),
                respuestaVerificada.getMensaje(),
                respuestaVerificada.getTopico().getId(),
                respuestaVerificada.getFechaCreacion(),
                respuestaVerificada.getUsuario().getId() );
    }
}
