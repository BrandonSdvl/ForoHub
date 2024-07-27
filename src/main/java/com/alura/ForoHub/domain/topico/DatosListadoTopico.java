package com.alura.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String estado,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getStatus() ? "Resuelto" : "Sin respuesta",
                topico.getAutor().getNombre(),
                topico.getCurso()
        );
    }
}
