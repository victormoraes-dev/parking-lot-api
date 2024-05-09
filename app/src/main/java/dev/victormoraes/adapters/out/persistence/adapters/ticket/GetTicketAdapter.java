package dev.victormoraes.adapters.out.persistence.adapters.ticket;

import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import dev.victormoraes.adapters.out.persistence.repositories.TicketRepository;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.usecases.ports.GetTicketPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static dev.victormoraes.adapters.mappers.TicketMapper.toDomainModel;

@Component
public class GetTicketAdapter implements GetTicketPort {

    private final TicketRepository ticketRepository;

    public GetTicketAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getTicketById(Long ticketId) {

        Optional<TicketEntity> ticketEntityOptional = ticketRepository.findById(ticketId);

        TicketEntity ticketEntity = ticketEntityOptional
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        return toDomainModel(ticketEntity);
    }
}
