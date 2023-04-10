package com.fin.fourfinapi.domain.service;

import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta salvar(Conta conta) {
        return contaRepository.save(conta);
    }

    public void excluir(Long contaId) {
        try {
            contaRepository.deleteById(contaId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe uma Conta com o código %d", contaId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Conta de código %d não pode ser removida, pois está em uso", contaId));
        }
    }
}
