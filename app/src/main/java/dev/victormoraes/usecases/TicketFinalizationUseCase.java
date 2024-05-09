package dev.victormoraes.usecases;

import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.FinalizeTicketPort;
import dev.victormoraes.usecases.ports.GetTicketPort;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class TicketFinalizationUseCase {

    private final FinalizeTicketPort finalizeTicketPort;
    private final GetTicketPort getTicketPort;


    public TicketFinalizationUseCase(FinalizeTicketPort finalizeTicketPort, GetTicketPort getTicketPort) {
        this.finalizeTicketPort = finalizeTicketPort;
        this.getTicketPort = getTicketPort;
    }

    public Result<Ticket> finalizeTicket(Long ticketId) {

        Ticket ticket = getTicketPort.getTicketById(ticketId);

        if (nonNull(ticket.getEndTime())) {
            return new Result<>(false, "Ticket is already finalized");
        }

        Ticket returnedTicket = finalizeTicketPort.finalizeTicket(ticketId);
        return new Result<>(true, returnedTicket);
    }
}
