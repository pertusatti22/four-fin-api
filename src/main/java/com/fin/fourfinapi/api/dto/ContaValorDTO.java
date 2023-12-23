package com.fin.fourfinapi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContaValorDTO {
    
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Boolean ativo;

    public ContaValorDTO(Long id, String nome, BigDecimal valor, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.ativo = ativo;
    }
}
