package com.fin.fourfinapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Conta {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private BigDecimal valorInicial;
    
    @Column
    private Boolean ativo = Boolean.TRUE;

    @OneToMany(mappedBy = "conta")
    private List<Transacao> transacoes;

    public BigDecimal calcularSaldo() {
        BigDecimal saldo = getValorInicial();

        if (transacoes != null) {
            for (Transacao transacao : transacoes) {
                if (transacao.getTipoTransacao() == TipoTransacao.ENTRADA) {
                    saldo = saldo.add(transacao.getValor());
                } else {
                    saldo = saldo.subtract(transacao.getValor());
                }
            }
        }

        return saldo;
    }
}
