package com.fin.fourfinapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transacao {
    @Id
    private Long id;

    @Column
    private TipoTransacao tipoTransacao;

    @Column
    private Date data;

    @Column
    private Categoria categoria;

    @Column
    private Conta conta;

    @Column
    private BigDecimal valor;

    @Column
    private String anotacao;
}
