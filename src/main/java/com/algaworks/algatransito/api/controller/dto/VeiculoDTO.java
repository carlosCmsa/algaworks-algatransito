package com.algaworks.algatransito.api.controller.dto;

import java.time.OffsetDateTime;
import com.algaworks.algatransito.domain.model.StatusVeiculoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoDTO {
    
    private String marca;

    private String modelo;

    private String codigoPlaca;

    private StatusVeiculoEnum status;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataApreensao;

    private int idProprietario;

}
