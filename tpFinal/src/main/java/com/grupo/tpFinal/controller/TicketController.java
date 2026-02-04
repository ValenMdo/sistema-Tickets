package com.grupo.tpFinal.controller;


import com.grupo.tpFinal.dto.TicketCreateDTO;
import com.grupo.tpFinal.dto.TicketsStatsDTO;
import com.grupo.tpFinal.enums.EstadoTicket;
import com.grupo.tpFinal.enums.Rol;
import com.grupo.tpFinal.model.Ticket;
import com.grupo.tpFinal.model.Usuario;
import com.grupo.tpFinal.service.TicketService;
import com.grupo.tpFinal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UsuarioService usuarioService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/lista")
    public List<Ticket> getTickets(){
        return ticketService.obtenerTickets();
    }

    @GetMapping("/stats")
    public TicketsStatsDTO getStats() {
        return ticketService.obtenerEstadisticas();
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(
            @RequestBody TicketCreateDTO dto
    ) {

        Usuario usuario = usuarioService.getUsuarioActual();

        // Validar rol
        // (!usuario.getRol().equals(Rol.TRABAJADOR))
       /*
        if (!Rol.TRABAJADOR.equals(usuario.getRol())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        */

        if (!Rol.TRABAJADOR.equals(usuario.getRol())) {
            throw new RuntimeException("Solo los trabajadores pueden crear tickets");
        }

        Ticket ticket = ticketService.crearTicket(
                dto.getTitulo(),
                dto.getDescripcion(),
                usuario
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PutMapping("/{id}/estado")
    public Ticket cambiarEstado(
            @PathVariable Long id,
            @RequestBody EstadoTicket estado) {

        return ticketService.cambiarEstado(id, estado);
    }

    @GetMapping("/test")
    public String test() {
        return "TicketController OK";
    }

}
