package com.eventfy.login.service;

import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.entity.Organizador;
import com.eventfy.login.repository.OrganizadorRepository;
import com.eventfy.login.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private PasswordEncoder passwordEncoder; // Para verificar a senha criptografada

    public LoginResponseDTO autenticar(OrganizadorAuth organizadorAuth) {
        // Verifica se o organizador existe pelo email
        Optional<Organizador> organizadorOptional = organizadorRepository.findByEmailOrganizador(organizadorAuth.getEmailOrganizador());

        if (organizadorOptional.isPresent()) {
            Organizador organizador = organizadorOptional.get();

            // Verifica se a senha é válida
            if (passwordEncoder.matches(organizadorAuth.getSenhaOrganizador(), organizador.getSenhaOrganizador())) {
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
        }

        // Caso o organizador não seja encontrado ou a senha não seja válida
        throw new RuntimeException("Email ou senha inválidos.");
    }
    public Optional<Organizador> findByEmailOrganizador(String email) {
        return organizadorRepository.findByEmailOrganizador(email);
    }
    // Adicione outros métodos necessários, como registro, atualização, etc.
}
