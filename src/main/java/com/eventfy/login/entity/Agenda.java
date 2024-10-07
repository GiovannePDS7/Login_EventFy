package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Agenda")

public class Agenda {

    @Id
    @Column(name = "Id_Agenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_agenda;

    @Column(name = "Calendario_Agenda")
    private Date calendario_agenda;

    @Column(name = "Horario_Agenda")
    private Time horario_agenda;

    @Column(name = "Descricao_Agenda")
    private String descricao_agenda;

}
