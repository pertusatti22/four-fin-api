package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.listar();
    }

    @GetMapping("/{categoriaId}")
    public Categoria buscar(@PathVariable Long categoriaId) {
        return categoriaRepository.buscar(categoriaId);
    }
}
