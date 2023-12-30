package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.api.dto.CategoriaValorDTO;
import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import com.fin.fourfinapi.domain.service.CadastroCategoriaService;
import com.fin.fourfinapi.domain.service.CategoriaTransacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CadastroCategoriaService cadastroCategoria;
    
    @Autowired
    private CategoriaTransacaoService categoriaTransacaoService;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
    
    @GetMapping("/totais")
    public List<CategoriaValorDTO> listarComValor() {
        return categoriaTransacaoService.listarCategoriaComValor();
    }
    
    @GetMapping("/{categoriaId}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long categoriaId) {
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);

        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria adicionar(@RequestBody Categoria categoria) {
        return cadastroCategoria.salvar(categoria);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria) {
        Optional<Categoria> categoriaAtualizada = categoriaRepository.findById(categoriaId);

        if (categoriaAtualizada.isPresent()) {
            BeanUtils.copyProperties(categoria, categoriaAtualizada.get(), "id");
            Categoria categoriaSalva = cadastroCategoria.salvar(categoriaAtualizada.get());
            return ResponseEntity.ok(categoriaSalva);
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
