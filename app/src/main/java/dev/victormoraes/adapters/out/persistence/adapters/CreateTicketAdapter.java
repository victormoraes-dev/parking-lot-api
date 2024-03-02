package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.TicketMapper;
import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import dev.victormoraes.adapters.out.persistence.repositories.TicketRepository;
import dev.victormoraes.adapters.out.persistence.repositories.UserRepository;
import dev.victormoraes.adapters.out.persistence.repositories.VehicleRepository;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.ports.CreateTicketPort;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class CreateTicketAdapter implements CreateTicketPort {

    private final TicketRepository ticketRepository;
    private final VehicleRepository vehicleRepository;
    private final TicketMapper ticketMapper;
    private final UserRepository userRepository;

    public CreateTicketAdapter(TicketRepository ticketRepository,
                               VehicleRepository vehicleRepository,
                               TicketMapper ticketMapper,
                               UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketMapper = ticketMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket, VehicleType vehicleType) {

        TicketEntity ticketEntity = ticketMapper.toEntityModel(ticket);

        var vehicleEntity = vehicleRepository.findByCode(ticket.getVehicle().getCode());
        if (vehicleEntity.isEmpty()) {
            throw new IllegalArgumentException(format("There is no vehicle for the code %s", ticket.getVehicle().getCode()));
        }
        ticketEntity.setVehicle(vehicleEntity.get());

        var userEntity = userRepository.findByUsername(ticket.getUser().getUsername());
        if (userEntity.isEmpty()) {
            throw new IllegalArgumentException(format("There is no user with the given username %s", ticket.getUser().getUsername()));
        }
        ticketEntity.setUser(userEntity.get());
        var newTicketEntity = ticketRepository.save(ticketEntity);

        return ticketMapper.toDomainModel(newTicketEntity, vehicleType);
    }
}
