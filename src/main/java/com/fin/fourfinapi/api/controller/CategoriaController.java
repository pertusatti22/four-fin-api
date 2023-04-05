package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import com.fin.fourfinapi.domain.service.CadastroCategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CadastroCategoriaService cadastroCategoria;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.listar();
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long categoriaId) {
        Categoria categoria = categoriaRepository.buscar(categoriaId);

        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar(@RequestBody Categoria categoria) {
        return cadastroCategoria.salvar(categoria);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaRepository.buscar(categoriaId);

        if (categoriaAtualizada != null) {
            BeanUtils.copyProperties(categoria, categoriaAtualizada, "id");
            cadastroCategoria.salvar(categoriaAtualizada);
            return ResponseEntity.ok(categoriaAtualizada);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Categoria> remover(@PathVariable Long categoriaId) {
        try {
            cadastroCategoria.excluir(categoriaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
