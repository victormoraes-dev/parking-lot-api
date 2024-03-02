package dev.victormoraes.adapters.out.persistence.repositories;

import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}

