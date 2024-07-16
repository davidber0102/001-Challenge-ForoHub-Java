package com.Porfile.__ForoHub_Java.Domain.Topico;
import com.Porfile.__ForoHub_Java.Domain.Curso.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record RegistroTopico(
        @NotBlank(message = "El título debe ser Unico")
        String titulo,
        @NotBlank(message = "Se debe registar un mensaje apropiado que no supere los 700 caracteres de longitud.")
        String mensaje ,
        @NotNull(message = "Seleccione uno de los Estados ´NO_RESPONDIDO´,´NO_SOLUCIONADO´, 'SOLUCIONADO'")
        Estado estado,
        @NotNull(message = "Utilice su ID de autor de usuario_Id")
        Long usuario_Id,
        @NotNull(message = "Recuerda utilizar el curso apropiado para tu publicación.")
        Curso curso,
        LocalDateTime fechaCreacion) {
}
