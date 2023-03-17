package com.fin.fourfinapi.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private TipoTransacao tipoTransacao;

    @Column
    private Date data;

    @Column
    private BigDecimal valor;

    @Column
    private String anotacao;
}
