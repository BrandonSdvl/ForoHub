package com.alura.ForoHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String clave) {
}
