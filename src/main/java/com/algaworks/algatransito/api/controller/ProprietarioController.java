package com.algaworks.algatransito.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@AllArgsConstructor
@RequestMapping("/api/v1/proprietarios")
@RestController
public class ProprietarioController {
    
    private ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long id) {
        return proprietarioRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Proprietario incluir(@RequestBody Proprietario requisicao) {
        return proprietarioRepository.save(requisicao);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Proprietario requisicao) {
        if(!proprietarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        requisicao.setId(id);
        Proprietario proprietario = proprietarioRepository.save(requisicao);

        return ResponseEntity.ok(proprietario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if(!proprietarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        proprietarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
