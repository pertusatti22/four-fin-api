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

import java.math.BigDecimal;
import java.util.Optional;

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

        Categoria categoria = categoriaRepository
                .findById(categoriaId)
                .orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        "A Categoria informada não existe."));

        Conta conta = contaRepository
                .findById(contaId)
                .orElseThrow(
                () ->  new EntidadeNaoEncontradaException(
                        "A Conta informada não existe."));

        transacao.setCategoria(categoria);
        transacao.setConta(conta);
        
        return transacaoRepository.save(transacao);
    }

    public void excluir(Long transacaoId) {
        try {
            transacaoRepository.deleteById(transacaoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe uma Transação com o código %d", transacaoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Transação de código %d não pode ser removido, pois está em uso", transacaoId));
        }
    }

    public BigDecimal somarValorPorCategoria(Long categoriaId) {
        return transacaoRepository.somarValorPorCategoria(categoriaId);
    }
    
    public BigDecimal somarValorPorConta(Long contaId) {
        Conta conta = contaRepository
                .findById(contaId)
                .orElseThrow(
                        () ->  new EntidadeNaoEncontradaException(
                                "A Conta informada não existe."));

        return conta.getValorInicial().add(transacaoRepository.somarValorPorConta(contaId));
    }
}
