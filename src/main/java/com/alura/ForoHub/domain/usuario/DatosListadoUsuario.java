package com.alura.ForoHub.domain.usuario;

public record DatosListadoUsuario(
        Long id,
        String nombre
) {
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}
