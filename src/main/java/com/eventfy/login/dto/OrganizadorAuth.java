package com.eventfy.login.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizadorAuth implements UserDetails {

    private Long id;
    private String emailOrganizador;
    private String senhaOrganizador;
    private String nomeOrganizador;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Se você não tiver um sistema de roles, pode deixar como null
    }

    @Override
    public String getPassword() {
        return senhaOrganizador;
    }

    @Override
    public String getUsername() {
        return emailOrganizador;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
