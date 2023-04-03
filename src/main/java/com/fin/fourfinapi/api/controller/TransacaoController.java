package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @GetMapping
    public List<Transacao> listar() {
        return transacaoRepository.listar();
    }

    @GetMapping("/{transacaoId}")
    public ResponseEntity<Transacao> buscar(@PathVariable Long transacaoId) {
        Transacao transacao = transacaoRepository.buscar(transacaoId);

        if(transacao != null){
            return ResponseEntity.ok(transacao);
        }
        return ResponseEntity.notFound().build();
    }
}
