package com.eventfy.login.service;

import com.eventfy.login.entity.Organizador;
import com.eventfy.login.repository.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Organizador organizador = organizadorRepository.findByEmailOrganizador(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return new org.springframework.security.core.userdetails.User(
                organizador.getEmailOrganizador(),
                organizador.getSenhaOrganizador(),
                new ArrayList<>() // Adicione authorities se necessário
        );
    }
}
