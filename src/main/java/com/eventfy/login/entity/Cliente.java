package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Cliente")

public class Cliente {

    @Id
    @Column(name = "Id_Cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(name = "Nome_Cliente")
    private String nome_cliente;

    @Column(name = "Data_Nasc_Cliente")
    private Date data_nasc_cliente;

    @Column(name = "Email_Cliente")
    private String email_cliente;

    @Column(name = "Contato_Cliente")
    private String contato_cliente;
}
