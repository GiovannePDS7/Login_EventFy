package com.eventfy.login.controller;

import com.eventfy.login.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping("/verificar-email")
    public ResponseEntity<?> verificarEmail(@RequestParam String email) {

        boolean existe = organizadorService.emailJaCadastrado(email);
        return ResponseEntity.ok().body(Collections.singletonMap("existe", existe));
    }
}
