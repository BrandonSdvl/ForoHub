package com.alura.ForoHub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Boolean estado
) {
}
