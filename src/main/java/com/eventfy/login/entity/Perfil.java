package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Perfil")

public class Perfil {

    @Id
    @Column(name = "Id_Perfil")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_perfil;

    @Column(name = "Tipo_Perfil")
    private String tipo_perfil;

    @Column(name = "Nome_Perfil")
    private String nome_perfil;

    @Column(name = "Contato_Perfil")
    private String contato_perfil;

    @Column(name = "Email_Perfil")
    private String email_perfil;

}
