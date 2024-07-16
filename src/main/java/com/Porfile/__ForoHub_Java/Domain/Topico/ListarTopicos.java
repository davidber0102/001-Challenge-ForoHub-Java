package com.Porfile.__ForoHub_Java.Domain.Topico;

import com.Porfile.__ForoHub_Java.Domain.Curso.Curso;
import com.Porfile.__ForoHub_Java.Domain.Usuario.Usuario;

import java.time.LocalDateTime;

public record ListarTopicos(
        Long id,
        String titulo,
        String mensaje,
        Estado estado,
        String usuario,
        String curso
        ) {

    public ListarTopicos(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getEstado(),
                topico.getUsuario().getNombre(),
                topico.getCurso().getNombre()         );
    }
}
