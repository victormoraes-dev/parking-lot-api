package dev.victormoraes.usecases;

import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.FetchTicketPort;
import org.springframework.stereotype.Component;

@Component
public class FetchingTicketUseCase {

    private final FetchTicketPort fetchTicketPort;

    public FetchingTicketUseCase(FetchTicketPort fetchTicketPort) {
        this.fetchTicketPort = fetchTicketPort;
    }

    public Result<Ticket> fetchTicketById(Long ticketId) {
        return fetchTicketPort.fetchTicketById(ticketId);
    }
}
