package com.eventfy.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Feedback")

public class Feedback {

    @Id
    @Column(name = "Id_Feedback")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_feedback;

    @Column(name = "Txt_Feedback")
    private String txt_feedback;

    @ManyToOne
    @Column(name = "Id_Evento")
    private Evento id_evento;

}
