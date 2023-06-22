package com.fin.fourfinapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Transacao {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String anotacao;

    @Column(nullable = false)
    private OffsetDateTime dataTransacao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipoTransacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Conta conta;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;
}
