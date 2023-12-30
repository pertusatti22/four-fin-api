package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.api.dto.ContaValorDTO;
import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.model.Transacao;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import com.fin.fourfinapi.domain.service.CadastroContaService;
import com.fin.fourfinapi.domain.service.ContaTransacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CadastroContaService cadastroConta;
    
    @Autowired
    private ContaTransacaoService contaTransacaoService;

    

    @GetMapping("/totais")
    public List<ContaValorDTO> listarComValor() {
        return contaTransacaoService.listarContaComValor();
    }
    
    @GetMapping
    public List<Conta> listar() {
        return contaRepository.findAll();
    }

    @GetMapping("/transacoes/{contaId}")
    public List<Transacao> listarTransacoesPorConta(@PathVariable Long contaId) {
        return contaTransacaoService.listarPorConta(contaId);
    }

    @GetMapping("/{contaId}")
    public ResponseEntity<Conta> buscar(@PathVariable Long contaId) {
        Optional<Conta> conta = contaRepository.findById(contaId);

        return conta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta adicionar(@RequestBody Conta conta) {
        return cadastroConta.salvar(conta);
    }

    @PutMapping("/{contaId}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long contaId, @RequestBody Conta conta) {
        Optional<Conta> contaAtualizada = contaRepository.findById(contaId);

        if(contaAtualizada.isPresent()) {
            BeanUtils.copyProperties(conta, contaAtualizada.get(), "id");
            Conta contaSalva = cadastroConta.salvar(contaAtualizada.get());
            return ResponseEntity.ok(contaSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{contaId}")
    public ResponseEntity<Conta> remover(@PathVariable Long contaId) {
        try {
            cadastroConta.excluir(contaId);
            return ResponseEntity.noContent().build();
            } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
