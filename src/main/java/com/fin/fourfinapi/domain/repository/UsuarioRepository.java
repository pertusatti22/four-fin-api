package com.fin.fourfinapi.domain.repository;
import com.fin.fourfinapi.domain.model.Usuario;

import java.util.List;

public interface UsuarioRepository {
    List<Usuario> listar();

    Usuario buscar(Long id);

    Usuario salvar(Usuario usuario);

    void remover(Usuario usuario);
}
