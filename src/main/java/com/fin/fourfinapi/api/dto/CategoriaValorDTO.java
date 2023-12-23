package com.fin.fourfinapi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CategoriaValorDTO {
    
    private Long id;
    private String nome;
    private BigDecimal valor;

    public CategoriaValorDTO(Long id, String nome, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }
}
