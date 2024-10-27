package com.eventfy.login.repository;

import com.eventfy.login.entity.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
    Optional<Organizador> findByEmailOrganizador(String email);
}
