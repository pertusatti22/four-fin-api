package com.fin.fourfinapi.jpa;

import com.fin.fourfinapi.domain.model.Conta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CadastroConta {

    @PersistenceContext
    private EntityManager manager;

    public List<Conta> listar() {
        return manager.createQuery("from Conta", Conta.class).getResultList();
    }

    public Conta buscar(Long id){
        return manager.find(Conta.class, id);
    }

    @Transactional
    public Conta salvar(Conta conta) {
        return manager.merge(conta);
    }

    @Transactional
    public void remover(Conta conta) {
        conta = buscar(conta.getId());
        manager.remove(conta);
    }
}
