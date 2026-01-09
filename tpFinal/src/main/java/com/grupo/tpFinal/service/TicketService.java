package com.grupo.tpFinal.service;


import com.grupo.tpFinal.dto.TicketsStatsDTO;
import com.grupo.tpFinal.enums.EstadoTicket;
import com.grupo.tpFinal.model.Ticket;
import com.grupo.tpFinal.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepo;

    public List<Ticket> obtenerTickets() {
        return ticketRepo.findAll();
    }

    public Ticket crearTicket(Ticket ticket) {
        ticket.setEstado(EstadoTicket.NO_ATENDIDO);
        return ticketRepo.save(ticket);
    }

    public Ticket cambiarEstado(Long id, EstadoTicket estado) {
        Ticket t = ticketRepo.findById(id).orElseThrow();
        t.setEstado(estado);
        return ticketRepo.save(t);
    }

    public TicketsStatsDTO obtenerEstadisticas() {

        long total = ticketRepo.count();
        long abiertos = ticketRepo.countByEstado("ABIERTO");
        long enProceso = ticketRepo.countByEstado("EN_PROCESO");
        long cerrados = ticketRepo.countByEstado("CERRADO");

        return new TicketsStatsDTO(total, abiertos, enProceso, cerrados);
    }
}
