package com.algaworks.algatransito.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algatransito.api.controller.dto.VeiculoDTO;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.service.VeiculoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RequestMapping(path = "/api/v1/veiculos")
@RestController
public class VeiculoController {
    
    private final VeiculoService veiculoService;

    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listar() {
        List<VeiculoDTO> veiculos = veiculoService.listar().stream()
            .map(veiculo -> mapper.map(veiculo, VeiculoDTO.class))
            .toList();

        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscar(@PathVariable Long id) {
        return veiculoService.buscar(id)
            .map(veiculo -> mapper.map(veiculo, VeiculoDTO.class))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo incluir(@Valid @RequestBody Veiculo veiculo) {
        return veiculoService.incluir(veiculo);
    }

    @PutMapping
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.ok(veiculoService.atualizar(id, veiculo));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        veiculoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
    
}
