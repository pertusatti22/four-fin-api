package com.fin.fourfinapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transacao {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String anotacao;

    @Column
    private LocalDate dataTransacao;

    @Column
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoTransacao tipoTransacao;

    @ManyToOne
    private Conta conta;

    @ManyToOne
    private Categoria categoria;
}
