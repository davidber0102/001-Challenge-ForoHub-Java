package com.Porfile.__ForoHub_Java.Domain.Curso;

public record ListadoCursos(
        Long id,
        String nombre,
        String categoria) {

    public ListadoCursos(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()        );
    }
}
