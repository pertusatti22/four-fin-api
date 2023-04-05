package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import com.fin.fourfinapi.domain.service.CadastroContaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CadastroContaService cadastroConta;

    @GetMapping
    public List<Conta> listar() {
        return contaRepository.listar();
    }

    @GetMapping("/{contaId}")
    public ResponseEntity<Conta> buscar(@PathVariable Long contaId) {
        Conta conta = contaRepository.buscar(contaId);

        if(conta != null){
        return ResponseEntity.ok(conta);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta adicionar(@RequestBody Conta conta) {
        return cadastroConta.salvar(conta);
    }

    @PutMapping("/{contaId}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long contaId, @RequestBody Conta conta) {
        Conta contaAtualizada = contaRepository.buscar(contaId);

        if(contaAtualizada != null) {
            BeanUtils.copyProperties(conta, contaAtualizada, "id");
            cadastroConta.salvar(contaAtualizada);
            return ResponseEntity.ok(contaAtualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{contaId}")
    public ResponseEntity<Conta> remover(@PathVariable Long contaId) {
        try {
            Conta conta = contaRepository.buscar(contaId);

            if(conta != null) {
                contaRepository.remover(conta);

                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
