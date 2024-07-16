package com.Porfile.__ForoHub_Java.Domain.Curso;

public record RegistroCurso(
        String nombre,
        String categoria) {

    public RegistroCurso(Curso curso) {
        this(
          curso.getNombre(),
          curso.getCategoria());
    }
}
