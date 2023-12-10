package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.repository.CategoriaRepository;
import com.fin.fourfinapi.domain.service.CadastroCategoriaService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class CategoriaControllerTest {
    @InjectMocks
    private CategoriaController categoriaController;
    @Mock
    private CategoriaRepository categoriaRepository;
    @Mock
    private CadastroCategoriaService cadastroCategoria;
    
    @Test
    void deveRetornarSucesso_QuandoListarCategorias() {
        Response response = RestAssured.get("/categorias");
        
        int statusCode = response.statusCode();
        
        assertEquals(200, statusCode);
    }
    
}
