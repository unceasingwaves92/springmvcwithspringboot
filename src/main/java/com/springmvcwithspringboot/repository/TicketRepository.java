package com.springmvcwithspringboot.repository;

import com.springmvcwithspringboot.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
