package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.in.dtos.ticket.TicketResponseDTO;
import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.domain.Ticket;

public class TicketMapper {

    public static TicketResponseDTO toResponseDTO(Ticket ticket) {

        return new TicketResponseDTO(ticket.getTicketId(),
                VehicleMapper.toDTO(ticket.getVehicle()),
                ticket.getStartTime(),
                ticket.getEndTime());
    }

    public static TicketEntity toEntityModel(Ticket ticket) {

        VehicleEntity vehicleEntity = VehicleMapper.toEntity(ticket.getVehicle(), ticket.getVehicle().getClass());

        return new TicketEntity(vehicleEntity);
    }

    public static Ticket toDomainModel(TicketEntity ticketEntity) {

        return new Ticket(ticketEntity.getTicketId(),
                ticketEntity.getVehicle().toDomain(),
                UserMapper.toDomainModel(ticketEntity.getUser()),
                ticketEntity.getStartTime());
    }
}
