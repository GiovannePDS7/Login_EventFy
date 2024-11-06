package com.eventfy.login.service;

import com.eventfy.login.dto.LoginRequestDTO;
import com.eventfy.login.dto.LoginResponseDTO;
import com.eventfy.login.dto.OrganizadorAuth;
import com.eventfy.login.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtToken;


    public LoginResponseDTO autenticar(LoginRequestDTO login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsuario(),
                        login.getSenha()
                )
        );

        var organizador = (OrganizadorAuth) authentication.getPrincipal();
        var token = jwtToken.gerar(organizador);
        return new LoginResponseDTO(
                token,
                organizador.getId(),
                organizador.getNomeOrganizador(),
                organizador.getEmailOrganizador(),
                null,
                null
        );
    }

}
