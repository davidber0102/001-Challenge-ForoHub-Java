package com.Porfile.__ForoHub_Java.Domain.Perfil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Perfil")
@Table(name="perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    private boolean activo;

    public Perfil(
            Long id,
            String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void ActualizarPerfil(ActualizarPerfil actualizarPerfil) {
        if (actualizarPerfil.nombre() != null) {
            this.nombre = actualizarPerfil.nombre();
        }
    }
    public void desActivarPerfil(){            this.activo = false;        }


}
