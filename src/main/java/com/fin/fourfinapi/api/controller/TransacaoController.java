package com.fin.fourfinapi.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.TransacaoRepository;
import com.fin.fourfinapi.domain.service.CadastroTransacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

        if (transacao != null) {
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
    public ResponseEntity<?> atualizar(@PathVariable Long transacaoId, @RequestBody Transacao transacao) {
        Transacao transacaoAtualizada = transacaoRepository.buscar(transacaoId);

        if (transacaoAtualizada != null) {
            BeanUtils.copyProperties(transacao, transacaoAtualizada, "id");
            try {
                cadastroTransacao.salvar(transacaoAtualizada);

                return ResponseEntity.ok(transacaoAtualizada);
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{transacaoId}")
    public ResponseEntity<Transacao> remover(@PathVariable Long transacaoId) {
        try {
            cadastroTransacao.excluir(transacaoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{transacaoId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long transacaoId,
                                              @RequestBody Map<String, Object> atributos) {
        Transacao atributosAtualizados = transacaoRepository.buscar(transacaoId);

        if(atributosAtualizados == null) {
            return ResponseEntity.notFound().build();
        }

        mergeTransacao(atributos, atributosAtualizados);

        return atualizar(transacaoId, atributosAtualizados);
    }

    private static void mergeTransacao(Map<String, Object> atributosOrigem, Transacao transacaoAtualizada) {
        ObjectMapper objectMapper = new ObjectMapper();
        Transacao transacaoOrigem = objectMapper.convertValue(atributosOrigem, Transacao.class);

        atributosOrigem.forEach((chave, valor) -> {
            Field field = ReflectionUtils.findField(Transacao.class, chave);
            field.setAccessible(true);
            Object valorAtualizado = ReflectionUtils.getField(field, transacaoOrigem);
            ReflectionUtils.setField(field, transacaoAtualizada, valorAtualizado);
        });
    }
}
