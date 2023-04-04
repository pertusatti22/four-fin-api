package com.fin.fourfinapi.infrastructure.repository;

import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransacaoRepositoryImpl implements TransacaoRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Transacao> listar() {
        return manager.createQuery("from Transacao", Transacao.class).getResultList();
    }

    @Override
    public Transacao buscar(Long id) {
        return manager.find(Transacao.class, id);
    }
    @Transactional
    @Override
    public Transacao salvar(Transacao transacao) {
        return manager.merge(transacao);
    }

    @Transactional
    @Override
    public void remover(Transacao transacao) {
        transacao = buscar(transacao.getId());
        manager.remove(transacao);
    }
}
