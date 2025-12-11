package com.algaworks.algatransito.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TesteController {
    
    @GetMapping("/api/v1/teste")
    public String testar() {
        return "Testando 123";
    }
    

}
