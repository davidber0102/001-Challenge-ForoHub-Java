package com.Porfile.__ForoHub_Java.Domain.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(
        @NotBlank (message = "Nombre")
        String nombre,
        @NotBlank(message = "Debe tener entre 10 y 15 caracteres.") @Pattern(regexp = "\\d{10,15}")
        String contrasenia) {
}
