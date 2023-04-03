package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Conta;
import com.fin.fourfinapi.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

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

}
