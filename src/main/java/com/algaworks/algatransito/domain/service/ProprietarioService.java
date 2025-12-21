package com.algaworks.algatransito.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProprietarioService {
    
    private final ProprietarioRepository proprietarioRepository;

    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    public Optional<Proprietario> buscar(Long id) {
        return proprietarioRepository.findById(id);
    }

    @Transactional
    public Proprietario incluir(Proprietario proprietario) {

        if(proprietarioRepository.existsByEmail(proprietario.getEmail())) {
            throw new NegocioException("Já existe um proprietário com o email informado.");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public Proprietario atualizar(Long id, Proprietario proprietario) {
        Proprietario proprietarioExistente = proprietarioRepository.findById(id)
            .orElseThrow(() -> new NegocioException("Nenhum proprietário encontrado para o identificador informado."));

        if(!proprietarioExistente.getEmail().equals(proprietario.getEmail())) {
            if(proprietarioRepository.existsByEmail(proprietario.getEmail())) {
                throw new NegocioException("Já existe um proprietário com o email informado.");
            }
        }

        proprietario.setId(id);
        
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void deletar(Long id) {
        if(!proprietarioRepository.existsById(id)) {
            throw new NegocioException("Nenhum proprietário encontrado para o identificador informado.");
        }

        proprietarioRepository.deleteById(id);
    }

}
