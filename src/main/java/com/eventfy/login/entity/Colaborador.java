package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Colaborador")

public class Colaborador {

    @Id
    @Column(name = "Id_Cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(name = "Tipo_Colaborador")
    private String tipo_colaborador;

    @Column(name = "Funcao_Colaborador")
    private String funcao_colaborador;

    @Column(name = "Nome_Colaborador")
    private String nome_colaborador;

}
