package com.grupo.tpFinal.service;


import com.grupo.tpFinal.dto.TicketsStatsDTO;
import com.grupo.tpFinal.enums.EstadoTicket;
import com.grupo.tpFinal.enums.Rol;
import com.grupo.tpFinal.model.Ticket;
import com.grupo.tpFinal.model.Usuario;
import com.grupo.tpFinal.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private UsuarioService usuarioService;

    public List<Ticket> obtenerTickets() {
        return ticketRepo.findAll();
    }

    public Ticket crearTicket(String titulo, String descripcion, Usuario trabajador ) {

        Ticket ticket = new Ticket();

        ticket.setTitulo(titulo);
        ticket.setDescripcion(descripcion);
        ticket.setCreador(trabajador);
        ticket.setEstado(EstadoTicket.NO_ATENDIDO);
        ticket.setHora(LocalDateTime.now());

        return ticketRepo.save(ticket);
    }

    public Ticket cambiarEstado(Long id, EstadoTicket estado) {
        Ticket t = ticketRepo.findById(id).orElseThrow();
        t.setEstado(estado);
        return ticketRepo.save(t);
    }

    public TicketsStatsDTO obtenerEstadisticas() {

        long total = ticketRepo.count();
        long noAtendidos = ticketRepo.countByEstado(EstadoTicket.NO_ATENDIDO);
        long enProceso = ticketRepo.countByEstado(EstadoTicket.EN_PROCESO);
        long cerrados = ticketRepo.countByEstado(EstadoTicket.CERRADO);

        return new TicketsStatsDTO(total, noAtendidos, enProceso, cerrados);
    }

    public Ticket tomarTicket(Long id, Usuario tecnico) {

        Ticket ticket = ticketRepo.findById(id).orElseThrow();

        if (ticket.getEstado() != EstadoTicket.NO_ATENDIDO) {
            throw new RuntimeException("El ticket ya fue tomado");
        }

        ticket.setEstado(EstadoTicket.EN_PROCESO);
        ticket.setTecnicoAsignado(tecnico);

        return ticketRepo.save(ticket);
    }

    public Ticket resolverTicket(Long id, Usuario tecnico) {

        Ticket ticket = ticketRepo.findById(id).orElseThrow();

        if (ticket.getEstado() != EstadoTicket.EN_PROCESO) {
            throw new RuntimeException("El ticket no está en proceso");
        }


        if (ticket.getTecnicoAsignado() == null ||
                !ticket.getTecnicoAsignado().getId().equals(tecnico.getId())) {
            throw new RuntimeException("No sos el técnico asignado");
        }

        ticket.setEstado(EstadoTicket.RESUELTO);
        return ticketRepo.save(ticket);
    }
}
