package com.Porfile.__ForoHub_Java.Domain.Curso;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private boolean activo;

    public Curso(RegistroCurso registroCurso) {
        this.activo = true;
        this.nombre = registroCurso.nombre();
        this.categoria = registroCurso.categoria();
    }

    public void cursoActualizado(ActualizarCurso actualizarCurso) {
        if (actualizarCurso.nombre() != null) {
            this.nombre = actualizarCurso.nombre();
        }
        if (actualizarCurso.categoria() != null) {
            this.categoria = actualizarCurso.categoria();
        }
    }
        public void desActivarCurso() {
            this.activo = false;    }

}
