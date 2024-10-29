package com.eventfy.login.controller;

import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.entity.Organizador;
import com.eventfy.login.security.JwtToken;
import com.eventfy.login.service.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private OrganizadorService organizadorService;

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
