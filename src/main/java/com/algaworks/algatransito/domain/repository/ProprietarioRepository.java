package com.algaworks.algatransito.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algatransito.domain.model.Proprietario;


@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    
    public Optional<Proprietario> findById(Long id);

    public Optional<Proprietario> findByNomeContaining(String nome);

    public boolean existsByEmail(String email);

}
