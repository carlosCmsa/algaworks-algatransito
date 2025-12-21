package com.algaworks.algatransito.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculoEnum;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VeiculoService {
    
    private final VeiculoRepository veiculoRepository;

    private final ProprietarioRepository proprietarioRepository;

    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscar(Long id) {
        return veiculoRepository.findById(id);
    }

    @Transactional
    public Veiculo incluir(Veiculo veiculo) {
        Proprietario proprietario = proprietarioRepository.findById(veiculo.getProprietario().getId())
            .orElseThrow(() -> new NegocioException("Proprietário não encontrado."));

        if(veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
            throw new NegocioException("Já existe um veiculo com a placa informada.");
        }

        veiculo.setProprietario(proprietario);
        veiculo.setStatus(StatusVeiculoEnum.REGULAR);
        veiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public Veiculo atualizar(Long id, Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
            .orElseThrow(() -> new NegocioException("Nenhum veiculo encontrado para o identificador informado."));

        if(!veiculoExistente.getPlaca().equals(veiculo.getPlaca())) {
            if(veiculoRepository.existsByPlaca(veiculo.getPlaca())) {
                throw new NegocioException("Já existe um veiculo com a placa informada.");
            }
        }

        veiculo.setId(id);

        return veiculoRepository.save(veiculo);
    }

    public void deletar(Long id) {
        if(!veiculoRepository.existsById(id)) {
            throw new NegocioException("Nenhum veiculo encontrado para o identificador informado.");
        }

        veiculoRepository.deleteById(id);
    }

}
