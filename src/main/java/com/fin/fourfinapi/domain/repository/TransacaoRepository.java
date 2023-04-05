package com.fin.fourfinapi.domain.repository;

import com.fin.fourfinapi.domain.model.Transacao;

import java.util.List;

public interface TransacaoRepository {

    List<Transacao> listar();

    Transacao buscar(Long id);

    Transacao salvar(Transacao transacao);

    void remover(Long id);
}
