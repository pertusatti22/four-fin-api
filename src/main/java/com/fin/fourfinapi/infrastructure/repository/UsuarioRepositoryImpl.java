package com.fin.fourfinapi.infrastructure.repository;

import com.fin.fourfinapi.domain.model.Usuario;
import com.fin.fourfinapi.domain.repository.UsuarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Usuario> listar() {
        return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }

    @Override
    public Usuario buscar(Long id) {
        return manager.find(Usuario.class, id);
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return manager.merge(usuario);
    }

    @Override
    public void remover(Usuario usuario) {
        usuario = buscar(usuario.getId());
        manager.remove(usuario);
    }
}
