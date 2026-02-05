package com.grupo.tpFinal.model;

import com.grupo.tpFinal.enums.EstadoTicket;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
    @JoinColumn(name = "id_creador", nullable = false)
    private Usuario creador;

    @ManyToOne
    @JoinColumn(name = "id_tecnico_actual")
    private Usuario tecnicoAsignado;

    private LocalDateTime hora;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }


    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public void setTecnicoAsignado(Usuario tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public Long getId() {
        return id;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getCreador() {
        return creador;
    }


}
