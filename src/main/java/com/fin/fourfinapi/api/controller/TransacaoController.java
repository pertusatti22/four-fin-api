package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import com.fin.fourfinapi.domain.service.CadastroTransacaoService;
import org.apache.catalina.valves.rewrite.ResolverImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CadastroTransacaoService cadastroTransacao;

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
    public ResponseEntity<?> adicionar(@RequestBody Transacao transacao) {
        try {
            transacao = cadastroTransacao.salvar(transacao);

            return ResponseEntity.status(HttpStatus.CREATED).body(transacao);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{transacaoId}")
    public ResponseEntity<Transacao> atualizar(@PathVariable Long transacaoId, @RequestBody Transacao transacao){
        Transacao transacaoAtualizada = transacaoRepository.buscar(transacaoId);

        if(transacaoAtualizada != null) {
            BeanUtils.copyProperties(transacao, transacaoAtualizada, "id");

            cadastroTransacao.salvar(transacaoAtualizada);

            return ResponseEntity.ok(transacaoAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{transacaoId}")
    public ResponseEntity<Transacao> remover(@PathVariable Long transacaoId) {
        try {
            Transacao transacao = transacaoRepository.buscar(transacaoId);

            if(transacao != null) {
                transacaoRepository.remover(transacao);

                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
