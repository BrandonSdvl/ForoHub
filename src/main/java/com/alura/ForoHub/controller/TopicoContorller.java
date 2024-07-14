package com.alura.ForoHub.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoContorller {
    @PostMapping
    public void registrarTopico(@RequestBody @Valid String datosRegistroTopico) {
        System.out.println(datosRegistroTopico);
    }
}
