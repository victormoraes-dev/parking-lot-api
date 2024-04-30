package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.result.Result;

public interface FetchTicketPort {

    Result<Ticket> fetchTicketById(Long ticketId);
}
