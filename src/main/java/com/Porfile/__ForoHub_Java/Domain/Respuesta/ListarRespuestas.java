package com.Porfile.__ForoHub_Java.Domain.Respuesta;

import com.Porfile.__ForoHub_Java.Domain.Topico.Topico;
import com.Porfile.__ForoHub_Java.Domain.Usuario.Usuario;

import java.time.LocalDateTime;

public record ListarRespuestas(
        Long id,
        String mensaje,
        Long topico_id,
        Long usuario_id,
        LocalDateTime fechaCreacion) {

    public ListarRespuestas(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getUsuario().getId(),
                respuesta.getFechaCreacion()
                                    );
    }
}
