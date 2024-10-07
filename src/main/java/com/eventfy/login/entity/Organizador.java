package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Organizador")

public class Organizador {

    @Id
    @Column(name = "Id_Organizador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_organizador;

    @Column(name = "Nome_Organizador")
    private String nome_organizador;

    @Lob
    @Column(name = "Foto_Organizador")
    private Byte foto_organizador;

    @Column(name = "Email_Organizador")
    private String email_organizador;

    @Column(name = "Senha_Organizador")
    private String senha_organizador;

    @Column(name = "Contato_Organizador")
    private String contato_organizador;

    @Column(name = "Data_Cadastro")
    private LocalDateTime data_cadastro;

}
