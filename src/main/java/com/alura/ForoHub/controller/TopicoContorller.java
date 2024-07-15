package com.alura.ForoHub.controller;

import com.alura.ForoHub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoContorller {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosListadoTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosListadoTopico datosListadoTopico = new DatosListadoTopico(topico);
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoTopico);
    }

    @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listarTopicos() {
        return ResponseEntity.ok(topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> buscarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoRepository.findById(id).ifPresent(topico -> topicoRepository.delete(topico));
        return ResponseEntity.noContent().build();
    }

}
