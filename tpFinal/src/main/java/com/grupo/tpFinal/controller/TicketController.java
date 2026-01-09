package com.grupo.tpFinal.controller;


import com.grupo.tpFinal.dto.TicketsStatsDTO;
import com.grupo.tpFinal.enums.EstadoTicket;
import com.grupo.tpFinal.model.Ticket;
import com.grupo.tpFinal.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService TicketService;

    public TicketController(TicketService ticketService) {
        TicketService = ticketService;
    }

    public List<Ticket> getTickets(){
        return TicketService.obtenerTickets();
    }

    @GetMapping("/stats")
    public TicketsStatsDTO getStats() {
        return TicketService.obtenerEstadisticas();
    }

    @PostMapping
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        return TicketService.crearTicket(ticket);
    }

    @PutMapping("/{id}/estado")
    public Ticket cambiarEstado(
            @PathVariable Long id,
            @RequestBody EstadoTicket estado) {

        return TicketService.cambiarEstado(id, estado);
    }
}
