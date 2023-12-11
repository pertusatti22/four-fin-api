package com.fin.fourfinapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;

    @Column
    public Long getId() {
        return id;
    }
    
    @OneToMany(mappedBy = "categoria")
    private List<Transacao> transacoes;

    public BigDecimal calcularTotalTransacoes() {
        BigDecimal total = BigDecimal.ZERO;

        if (transacoes != null) {
            for (Transacao transacao : transacoes) {
                total = total.add(transacao.getValor());
            }
        }

        return total;
    }
}
