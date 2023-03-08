package com.fin.fourfinapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigInteger;
import java.util.Objects;


@Entity
public class Conta {
    @Id
    private Long id;

    @Column
    private String nome;

    @Column
    private BigInteger valorInicial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigInteger valorInicial) {
        this.valorInicial = valorInicial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conta conta)) return false;
        return getId().equals(conta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
