package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Evento")

public class Evento {

    @Id
    @Column(name = "Id_Evento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_evento;

    @Column(name = "Nome_Evento")
    private String nome_evento;

    @Column(name = "Data_Evento")
    private LocalDateTime data_evento;

    @Column(name = "Local_Evento")
    private String local_evento;

    @Column(name = "Horario_Evento")
    private Time horario_evento;

}
