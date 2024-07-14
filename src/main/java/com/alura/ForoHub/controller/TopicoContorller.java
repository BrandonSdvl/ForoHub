package com.alura.ForoHub.controller;

import com.alura.ForoHub.domain.topico.DatosListadoTopico;
import com.alura.ForoHub.domain.topico.DatosRegistroTopico;
import com.alura.ForoHub.domain.topico.Topico;
import com.alura.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoContorller {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        topicoRepository.save(new Topico(datosRegistroTopico));
    }

    @GetMapping
    public List<DatosListadoTopico> listarTopicos() {
        return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
    }

    @GetMapping("/{id}")
    public DatosListadoTopico buscarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return new DatosListadoTopico(topico);
    }
}
