package com.fin.fourfinapi.domain.service;

import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroTransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ContaRepository contaRepository;

    public Transacao salvar(Transacao transacao){
        Long categoriaId = transacao.getCategoria().getId();
        Long contaId = transacao.getConta().getId();

        Categoria categoria = categoriaRepository.buscar(categoriaId);
        Conta conta = contaRepository.buscar(contaId);

        if (categoria == null || conta == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("A Categoria ou Conta informada não existe."));
        }


        return transacaoRepository.salvar(transacao);
    }

    public void excluir(Long transacaoId) {
        try {
            transacaoRepository.remover(transacaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe uma Transação com o código %d", transacaoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Transação de código %d não pode ser removido, pois está em uso", transacaoId));
        }
    }
}
