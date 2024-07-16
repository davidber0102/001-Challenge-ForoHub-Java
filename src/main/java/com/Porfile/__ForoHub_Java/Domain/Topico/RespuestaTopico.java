package com.Porfile.__ForoHub_Java.Domain.Topico;

import com.Porfile.__ForoHub_Java.Domain.Curso.Curso;

import java.time.LocalDateTime;

public record RespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        Long usuario_id,
        String curso,
        LocalDateTime fechaCreacion) {
    public RespuestaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getUsuario().getId(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion() );
    }
}
