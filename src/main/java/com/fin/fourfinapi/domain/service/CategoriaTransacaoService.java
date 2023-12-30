package com.fin.fourfinapi.domain.service;

import com.fin.fourfinapi.api.dto.CategoriaValorDTO;
import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaTransacaoService {
    
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<CategoriaValorDTO> listarCategoriaComValor() {
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
                .map(categoria -> new CategoriaValorDTO(
                        categoria.getId(),
                        categoria.getNome(),
                        somarValorPorCategoria(categoria.getId())
                ))
                .collect(Collectors.toList());
    }


    public BigDecimal somarValorPorCategoria(Long categoriaId) {
        return transacaoRepository.somarValorPorCategoria(categoriaId);
    }
}
