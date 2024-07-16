package com.Porfile.__ForoHub_Java.Domain.Respuesta;

import com.Porfile.__ForoHub_Java.Domain.Topico.Topico;
import com.Porfile.__ForoHub_Java.Domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Boolean solucion = false;
    private boolean activo;

    public Respuesta(
            Long id,
            String mensaje,
            Topico topico,
            LocalDateTime fechaCreacion,
            Usuario usuario            ) {
        this.id = id;
        this.mensaje = mensaje;
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
    }


    public void ActualizacionRespuesta(ActualizacionRespuesta actualizacionRespuesta){
        if(actualizacionRespuesta.mensaje() != null){
            this.mensaje = actualizacionRespuesta.mensaje();
        }
    }

    public void desActivarRespuesta(){this.activo=false;    }
}
