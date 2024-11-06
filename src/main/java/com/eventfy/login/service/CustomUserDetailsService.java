package com.eventfy.login.service;

import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.entity.Organizador;
import com.eventfy.login.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscando o organizador pelo email
        Organizador organizador = organizadorRepository.findByEmailOrganizador(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
        // Retornando um objeto UserDetails com email e senha


        return OrganizadorAuth.builder().id(organizador.getIdOrganizador())
                .nomeOrganizador(organizador.getNomeOrganizador())
                .senhaOrganizador(organizador.getSenhaOrganizador())
                .emailOrganizador(organizador.getEmailOrganizador())
                .build();

    }
}
