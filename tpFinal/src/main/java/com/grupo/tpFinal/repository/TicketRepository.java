package com.grupo.tpFinal.repository;

import com.grupo.tpFinal.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    long countByEstado(String estado);
}
