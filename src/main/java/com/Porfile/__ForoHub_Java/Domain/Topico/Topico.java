package com.Porfile.__ForoHub_Java.Domain.Topico;

import com.Porfile.__ForoHub_Java.Domain.Curso.Curso;
import com.Porfile.__ForoHub_Java.Domain.Respuesta.Respuesta;
import com.Porfile.__ForoHub_Java.Domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Topico")
@Table(name="topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.NO_RESPONDIDO;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "topico_id", referencedColumnName = "id")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Topico(
            Long id,
            String titulo,
            String mensaje,
            LocalDateTime fechaCreacion,
            Usuario usuario,
            Curso curso ){
        this.id = id;
        this.titulo = titulo;
        this.mensaje =mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarTopico(ActualizarTopico actualizar){
        if(actualizar.titulo() != null){
            this.titulo = actualizar.titulo();}

        if(actualizar.mensaje() != null){
            this.mensaje = actualizar.mensaje();        }

        if(actualizar.estado() != null){
            this.estado = actualizar.estado();        }

        if(actualizar.autorId() != null){
            this.usuario = usuario; }

        if(actualizar.cursoId() != null){
            this.curso = curso;        }
    }

    public void desActivarTopico() {
        this.activo = false;
    }


}
