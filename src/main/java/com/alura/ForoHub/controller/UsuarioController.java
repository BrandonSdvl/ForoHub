package com.alura.ForoHub.controller;

import com.alura.ForoHub.domain.usuario.DatosAutenticacionUsuario;
import com.alura.ForoHub.domain.usuario.DatosListadoUsuario;
import com.alura.ForoHub.domain.usuario.Usuario;
import com.alura.ForoHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registrar")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario, UriComponentsBuilder uriComponentsBuilder) {
        if (usuarioRepository.findByNombre(datosAutenticacionUsuario.nombre()) != null) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está registrado.");
        }

        Usuario usuario = usuarioRepository.save(new Usuario(datosAutenticacionUsuario.nombre(), passwordEncoder.encode(datosAutenticacionUsuario.clave())));
        DatosListadoUsuario datosListadoUsuario = new DatosListadoUsuario(usuario);

        URI url = uriComponentsBuilder.path("login").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoUsuario);
    }
}
