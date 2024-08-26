package com.eventfy.login.controller;

import com.eventfy.login.entity.Post;
import com.eventfy.login.repository.PostRepository;
import com.eventfy.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.eventfy.login.entity.Usuario;
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> listarUsuario() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        var resposta = repository.findById(id);
        if (resposta.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resposta.get());
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        Usuario savedUsuario = repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable long id, @RequestBody Usuario usuario) {
        var existe = repository.findById(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setId(id); // Certifique-se de que o ID seja o mesmo do que está sendo atualizado
        Usuario updatedUsuario = repository.save(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }
}
