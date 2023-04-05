package com.fin.fourfinapi.infrastructure.repository;

import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContaRepositoryImpl implements ContaRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Conta> listar() {
        return manager.createQuery("from Conta", Conta.class).getResultList();
    }

    @Override
    public Conta buscar(Long id) {
        return manager.find(Conta.class, id);
    }

    @Transactional
    @Override
    public Conta salvar(Conta conta) {
        return manager.merge(conta);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Conta conta = buscar(id);

        if(conta == null){
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(conta);
    }
}
