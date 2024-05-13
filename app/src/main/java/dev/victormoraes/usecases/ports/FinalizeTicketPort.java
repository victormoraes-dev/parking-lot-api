package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Ticket;

public interface FinalizeTicketPort {
    Ticket finalizeTicket(Long ticketId);
}
