package com.eventfy.login.controller;

import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.entity.Organizador;
import com.eventfy.login.security.JwtToken;
import com.eventfy.login.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/organizadores")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private OrganizadorService organizadorService;
    @GetMapping("/verificar-email")
    public ResponseEntity<?> verificarEmail(@RequestParam String email) {

        boolean existe = organizadorService.emailJaCadastrado(email);
        return ResponseEntity.ok().body(Collections.singletonMap("existe", existe));
    }

    @PostMapping
    public LoginResponseDTO authenticate(@RequestBody OrganizadorAuth organizadorAuth) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        organizadorAuth.getEmailOrganizador(),
                        organizadorAuth.getSenhaOrganizador()
                )
        );

        // Recupera o organizador autenticado
        Organizador organizador = organizadorService.findByEmailOrganizador(organizadorAuth.getEmailOrganizador())
                .orElseThrow(() -> new RuntimeException("Organizador não encontrado."));

        // Gerar o token JWT
        String jwt = jwtToken.gerar((UserDetails) authentication.getPrincipal());

        // Retornar as informações do organizador juntamente com o token
        return new LoginResponseDTO(
                jwt,
                organizador.getIdOrganizador(),
                organizador.getNomeOrganizador(),
                organizador.getEmailOrganizador(),
                organizador.getContatoOrganizador(),
                organizador.getFotoOrganizador()
        );
    }
}
