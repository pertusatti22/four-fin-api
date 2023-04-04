package com.fin.fourfinapi.infrastructure.repository;

import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaRepositoryImpl implements CategoriaRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Categoria> listar() {
        return manager.createQuery("from Categoria", Categoria.class).getResultList();
    }

    @Override
    public Categoria buscar(Long id) {
        return manager.find(Categoria.class, id);
    }

    @Transactional
    @Override
    public Categoria salvar(Categoria categoria) {
        return manager.merge(categoria);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Categoria categoria = buscar(id);

        if(categoria == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(categoria);

    }
}
