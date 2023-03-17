package com.fin.fourfinapi.domain.repository;

import com.fin.fourfinapi.domain.model.Conta;

import java.util.List;

public interface ContaRepository {

    List<Conta> listar();

    Conta buscar(Long id);

    Conta salvar(Conta conta);

    void remover(Conta conta);


}
