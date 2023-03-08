package com.fin.fourfinapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;

@Entity
public class Categoria {
    @Id
    private Long id;

    @Column
    private String nome;

    @Column
    private Categoria categoriaPai;

    @Column
    private List<Categoria> categoriasFilhas;

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

    public Categoria getCategoriaPai() {
        return categoriaPai;
    }

    public void setCategoriaPai(Categoria categoriaPai) {
        this.categoriaPai = categoriaPai;
    }

    public List<Categoria> getCategoriasFilhas() {
        return categoriasFilhas;
    }

    public void setCategoriasFilhas(List<Categoria> categoriasFilhas) {
        this.categoriasFilhas = categoriasFilhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria categoria)) return false;
        return getId().equals(categoria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
