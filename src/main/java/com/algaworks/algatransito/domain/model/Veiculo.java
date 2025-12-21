package com.algaworks.algatransito.domain.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import com.algaworks.algatransito.domain.validations.ValidationGroupVeiculo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    @Size(max = 7)
    @Column(name = "placa")
    private String placa;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVeiculoEnum status;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "data_cadastro")
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "data_apreensao")
    private OffsetDateTime dataApreensao;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroupVeiculo.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "proprietario_id", referencedColumnName = "id")
    private Proprietario proprietario;
    
}
