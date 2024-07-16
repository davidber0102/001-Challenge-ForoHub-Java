package com.Porfile.__ForoHub_Java.Domain.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegistroUsuario(
    @NotBlank
    String nombre,
    @NotBlank
    @Email
    String email,
    @NotBlank(message = "Debe tener entre 10 y 15 caracteres.") @Pattern(regexp = "\\d{10,15}")
    String contrasenia){
}
