package com.algaworks.algatransito.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algatransito.domain.model.Proprietario;

@RestController
public class ProprietarioController {
    
    @GetMapping("/api/v1/proprietarios")
    public List<Proprietario> listar() {
        Proprietario proprietario1 = new Proprietario();
        proprietario1.setId(1L);
        proprietario1.setNome("Pedro Sampaio");
        proprietario1.setEmail("pedro.sampaio@gmail.com");
        proprietario1.setTelefone("1140028922");

        Proprietario proprietario2 = new Proprietario();
        proprietario2.setId(2L);
        proprietario2.setNome("João Pedro");
        proprietario2.setEmail("joao.pedro@gmail.com");
        proprietario2.setTelefone("1140028922");

        return List.of(proprietario1, proprietario2);
    }

}
