package com.fin.fourfinapi.api.controller;

import com.fin.fourfinapi.domain.exception.EntidadeEmUsoException;
import com.fin.fourfinapi.domain.exception.EntidadeNaoEncontradaException;
import com.fin.fourfinapi.domain.model.Usuario;
import com.fin.fourfinapi.domain.repository.UsuarioRepository;
import com.fin.fourfinapi.domain.service.CadastroUsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        if (usuario != null) {
            return ResponseEntity.ok(usuario.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionar(@RequestBody Usuario usuario) {

        return cadastroUsuario.salvar(usuario);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long usuarioId, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioAtualizado = usuarioRepository.findById(usuarioId);

        if (usuarioAtualizado != null) {
            BeanUtils.copyProperties(usuario, usuarioAtualizado.get(), "id");
            Usuario usuarioSalvo = cadastroUsuario.salvar(usuarioAtualizado.get());
            return ResponseEntity.ok(usuarioSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Usuario> remover(@PathVariable Long usuarioId) {
        try {
            cadastroUsuario.excluir(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
