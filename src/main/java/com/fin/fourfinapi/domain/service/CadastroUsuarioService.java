package com.fin.fourfinapi.domain.service;

import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Usuario;
import com.fin.fourfinapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.salvar(usuario);
    }

    public void excluir(Long usuarioId) {
        try {
            usuarioRepository.remover(usuarioId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um Usuario com o código %d", usuarioId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Usuario de código %d não pode ser removido, pois está em uso", usuarioId));
        }
    }
}
