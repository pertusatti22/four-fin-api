package com.fin.fourfinapi.domain.service;

import com.fin.fourfinapi.api.dto.CategoriaValorDTO;
import com.fin.fourfinapi.api.dto.ContaValorDTO;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaContaService {

    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<CategoriaValorDTO> listarCategoriaComValor() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(categoria -> new CategoriaValorDTO(
                        categoria.getNome(),
                        somarValorPorCategoria(categoria.getId())
                ))
                .collect(Collectors.toList());
    }

    public List<ContaValorDTO> listarContaComValor() {
        List<Conta> contas = contaRepository.findAll();

        return contas.stream()
                .map(conta -> new ContaValorDTO(
                        conta.getNome(),
                        somarValorPorConta(conta.getId())
                ))
                .collect(Collectors.toList());
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
