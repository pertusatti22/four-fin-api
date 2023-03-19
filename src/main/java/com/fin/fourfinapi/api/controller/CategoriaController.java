package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.model.Categoria;
import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public List<Categoria> listar() {
        return categoriaRepository.listar();
    }
}
