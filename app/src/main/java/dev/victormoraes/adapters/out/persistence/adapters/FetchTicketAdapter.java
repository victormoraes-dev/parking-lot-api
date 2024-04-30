package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.UserMapper;
import dev.victormoraes.adapters.out.persistence.repositories.TicketRepository;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.FetchTicketPort;
import org.springframework.stereotype.Component;

@Component
public class FetchTicketAdapter implements FetchTicketPort {

    private final TicketRepository ticketRepository;

    public FetchTicketAdapter(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Result<Ticket> fetchTicketById(Long ticketId) {

        var entity = ticketRepository.findById(ticketId);

        if (entity.isEmpty()) {
            return new Result<>(false, "Ticket not found");
        }

        var ticketEntity = entity.get();
        return new Result<>(true, new Ticket(ticketEntity.getTicketId(),
                ticketEntity.getVehicle().toDomain(),
                UserMapper.toDomainModel(ticketEntity.getUser()),
                ticketEntity.getStartTime()));
    }
}
