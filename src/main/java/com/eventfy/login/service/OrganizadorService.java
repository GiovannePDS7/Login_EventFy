package com.eventfy.login.service;

import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.entity.Organizador;
import com.eventfy.login.repository.OrganizadorRepository;
import com.eventfy.login.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public boolean emailJaCadastrado(String email) {
        return organizadorRepository.findByEmailOrganizador(email).isPresent();
    }
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager; // Para autenticação via Spring Security

    public LoginResponseDTO autenticar(OrganizadorAuth organizadorAuth) {
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
                organizador.getSenhaOrganizador(),
                organizador.getNomeOrganizador()
        ));

        // Retornar os dados necessários
        return new LoginResponseDTO(
                token,
                organizador.getIdOrganizador(),
                organizador.getNomeOrganizador(),
                organizador.getEmailOrganizador(),
                organizador.getContatoOrganizador(),
                organizador.getFotoOrganizador()
        );
    }

    public Optional<Organizador> findByEmailOrganizador(String email) {
        return organizadorRepository.findByEmailOrganizador(email);
    }

    // Adicione outros métodos necessários, como registro, atualização, etc.
}
