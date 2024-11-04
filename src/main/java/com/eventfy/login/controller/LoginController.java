package com.eventfy.login.controller;

import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.entity.Organizador;
import com.eventfy.login.repository.OrganizadorRepository;
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
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private OrganizadorService organizadorService;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody OrganizadorAuth organizadorAuth) {
        // Autentica usando o AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        organizadorAuth.getEmailOrganizador(),
                        organizadorAuth.getSenhaOrganizador()
                )
        );

        // Recupera o organizador autenticado
        Organizador organizador = organizadorRepository.findByEmailOrganizador(organizadorAuth.getEmailOrganizador())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos."));

        // Gerar o token JWT
        String token = jwtToken.gerar(new OrganizadorAuth(
                organizador.getIdOrganizador(),
                organizador.getEmailOrganizador(),
                "",  // Não inclua a senha no token
                organizador.getNomeOrganizador()
        ));

        // Retornar as informações do organizador juntamente com o token
        LoginResponseDTO response = new LoginResponseDTO(
                token,
                organizador.getIdOrganizador(),
                organizador.getNomeOrganizador(),
                organizador.getEmailOrganizador(),
                organizador.getContatoOrganizador(),
                organizador.getFotoOrganizador()
        );

        return ResponseEntity.ok(response); // Retorna 200 OK com os dados
    }

}
