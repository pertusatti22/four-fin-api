package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{categoriaId}")
    public Categoria buscar(@PathVariable Long categoriaId) {
        return categoriaRepository.buscar(categoriaId);
    }
}
