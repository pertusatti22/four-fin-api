package com.fin.fourfinapi.domain.repository;

import com.fin.fourfinapi.domain.model.Categoria;

import java.util.List;

public interface CategoriaRepository {
    List<Categoria> listar();

    Categoria buscar(Long id);

    Categoria salvar(Categoria categoria);

    void remover(Long categoriaId);
}

