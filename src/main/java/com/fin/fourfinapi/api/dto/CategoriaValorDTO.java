package com.fin.fourfinapi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CategoriaValorDTO {
    
    private String nome;
    private BigDecimal valor;

    public CategoriaValorDTO(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
