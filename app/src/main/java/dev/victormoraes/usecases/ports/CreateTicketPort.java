package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.vehicle.VehicleType;

public interface CreateTicketPort {

    public Ticket createTicket(Ticket ticket, VehicleType vehicleType);
}
