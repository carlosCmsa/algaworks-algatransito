package com.algaworks.algatransito.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "veiculo")
@Entity
public class Veiculo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "marca")
    private String marca;

    @NotBlank
    @Size(max = 20)
    @Column(name = "modelo")
    private String modelo;

    @NotBlank
    @Size(max = 7)
    @Column(name = "placa")
    private String placa;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVeiculoEnum status;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "data_apreensao")
    private LocalDateTime dataApreensao;

    @ManyToOne
    @JoinColumn(name = "proprietario_id", referencedColumnName = "id")
    private Proprietario proprietario;
    
}
