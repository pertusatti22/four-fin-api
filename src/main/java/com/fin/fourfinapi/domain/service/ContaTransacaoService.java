package com.fin.fourfinapi.domain.service;

import com.fin.fourfinapi.api.dto.ContaValorDTO;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaTransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ContaRepository contaRepository;
    
    public List<Transacao> listarPorConta(Long contaId){
        Optional<Conta> conta = contaRepository.findById(contaId);
        
        List<Transacao> transacaos = transacaoRepository.findAllByConta(conta);
        
        return transacaos;
    }
    
    public List<ContaValorDTO> listarContaComValor() {
        List<Conta> contas = contaRepository.findAll();

        return contas.stream()
                .map(conta -> new ContaValorDTO(
                        conta.getId(),
                        conta.getNome(),
                        somarValorPorConta(conta.getId()),
                        conta.getAtivo()
                ))
                .collect(Collectors.toList());
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
