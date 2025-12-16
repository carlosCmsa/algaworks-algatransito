package com.algaworks.algatransito.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.service.ProprietarioService;

import jakarta.validation.Valid;
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
    
    private ProprietarioService proprietarioService;

    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long id) {
        return proprietarioService.buscar(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Proprietario incluir(@Valid @RequestBody Proprietario requisicao) {
        return proprietarioService.incluir(requisicao);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, 
                                        @Valid @RequestBody Proprietario requisicao) {
        return ResponseEntity.ok(proprietarioService.atualizar(id, requisicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        proprietarioService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(exception = NegocioException.class)
    public ResponseEntity<String> handlerNegocioException(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
