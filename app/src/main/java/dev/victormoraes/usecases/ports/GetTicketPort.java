package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Ticket;

public interface GetTicketPort {
    Ticket getTicketById(Long ticketId);
}