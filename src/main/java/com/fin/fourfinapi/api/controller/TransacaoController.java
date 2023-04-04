package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao adicionar(@RequestBody Transacao transacao) {
        return transacaoRepository.salvar(transacao);
    }

    @PutMapping("/{transacaoId}")
    public ResponseEntity<Transacao> atualizar(@PathVariable Long transacaoId, @RequestBody Transacao transacao){
        Transacao transacaoAtualizada = transacaoRepository.buscar(transacaoId);

        if(transacaoAtualizada != null) {
            BeanUtils.copyProperties(transacao, transacaoAtualizada, "id");

            transacaoRepository.salvar(transacaoAtualizada);

            return ResponseEntity.ok(transacaoAtualizada);
        }
        return ResponseEntity.notFound().build();
    }
}
