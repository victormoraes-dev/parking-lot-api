package dev.victormoraes.adapters.out.persistence.adapters.ticket;

import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import dev.victormoraes.adapters.out.persistence.repositories.TicketRepository;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.usecases.ports.FinalizeTicketPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static dev.victormoraes.adapters.mappers.TicketMapper.toDomainModel;

@Component
public class FinalizeTicketAdapter implements FinalizeTicketPort {

    private final TicketRepository ticketRepository;

    public FinalizeTicketAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket finalizeTicket(Long ticketId) {

        Optional<TicketEntity> ticketEntityOptional = ticketRepository.findById(ticketId);

        TicketEntity ticketEntity = ticketEntityOptional
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        ticketEntity.finalizeTicket();
        TicketEntity updatedTicketEntity = ticketRepository.save(ticketEntity);

        return toDomainModel(updatedTicketEntity);
    }
}
