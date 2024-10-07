package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Ingresso")

public class Ingresso {

    @Id
    @Column(name = "Id_Ingresso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ingresso;

    @Column(name = "Nome_Ingresso")
    private String nome_ingresso;

    @Column(name = "CPF_Ingresso")
    private String cpf_ingresso;

    @Column(name = "Tipo_Ingresso")
    private String tipo_ingresso;

    @Column(name = "Data_Compra")
    private Date data_compra;

    @ManyToOne
    @Column(name = "Data_Evento")
    private Evento data_evento;

}
