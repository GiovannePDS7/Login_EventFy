package com.eventfy.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.eventfy.login.repository.PostRepository;
import com.eventfy.login.entity.Post;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository repository;

    @GetMapping
    public List<Post> listarPosts(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (resposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(resposta.get());
        }
    }
    public ResponseEntity<Post> inserir(@RequestBody Post post){
        repository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(post));
    }

    public ResponseEntity<Post> atualizar(@PathVariable long id, @RequestBody Post post){
        var existe = repository.findById(id);
        if(!existe.isPresent())
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(repository.save(post));
    }
}
