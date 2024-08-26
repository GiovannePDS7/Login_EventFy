package com.eventfy.login.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TBL_USUARIO")

/*
CREATE TABLE usuario (
        ID_usuario INT PRIMARY KEY IDENTITY,
        Nome CHAR(50),
        Email VARCHAR(50),
        Senha VARCHAR(50),
        Telefone VARCHAR(255),
        Data_Cadastro DATE
);
*/
public class Usuario {
    @Id
    @Column(name = "ID_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "Nome")
    private String nome;

    @Column(name = "Email")
    private String email;

    @Column(name = "Senha")
    private String senha;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Data_Cadastro")
    private LocalDate dataCadastro;

}
