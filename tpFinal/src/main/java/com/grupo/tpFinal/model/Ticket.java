package com.grupo.tpFinal.model;

import com.grupo.tpFinal.enums.EstadoTicket;
import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    @ManyToOne
    private Usuario creador;

    @ManyToOne
    private Usuario tecnicoAsignado;

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }
}
