package com.eventfy.login.service;

import com.eventfy.login.entity.Organizador;
import com.eventfy.login.repository.OrganizadorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class CustomUserDetailsService implements UserDetailsService {
    private OrganizadorRepository organizadorRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscando o organizador pelo email
        Organizador organizador = organizadorRepository.findByEmailOrganizador(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
        // Retornando um objeto UserDetails com email e senha

        return new User(
                organizador.getEmailOrganizador(),
                organizador.getSenhaOrganizador(), // A senha deve estar criptografada
                new ArrayList<>() // Adicione authorities se necessário
        );
    }
}
