package com.eventfy.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import com.eventfy.login.repository.PostRepository;
import com.eventfy.login.entity.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @GetMapping
    public List<Post> listarPosts() {
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
    public ResponseEntity<Post> inserir(@RequestBody Post post) {
        Post savedPost = repository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizar(@PathVariable long id, @RequestBody Post post) {
        var existe = repository.findById(id);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        post.setId(id); // Certifique-se de que o ID seja o mesmo do que está sendo atualizado
        Post updatedPost = repository.save(post);
        return ResponseEntity.ok(updatedPost);
    }
}
