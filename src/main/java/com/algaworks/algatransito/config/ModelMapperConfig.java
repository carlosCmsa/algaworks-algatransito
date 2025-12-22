package com.algaworks.algatransito.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algatransito.api.controller.dto.VeiculoDTO;
import com.algaworks.algatransito.domain.model.Veiculo;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper producer() {
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.createTypeMap(Veiculo.class, VeiculoDTO.class)
            .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoDTO::setCodigoPlaca));

        return modelMapper;
    }

}
