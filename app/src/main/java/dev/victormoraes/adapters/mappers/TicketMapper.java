package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.in.dtos.TicketResponseDTO;
import dev.victormoraes.adapters.out.persistence.entities.TicketEntity;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.domain.Ticket;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleFactory;
import dev.victormoraes.domain.vehicle.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    private final VehicleMapper vehicleMapper;

    public TicketMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    public TicketResponseDTO toResponseDTO(Ticket ticket, VehicleType vehicleType) {

        TicketResponseDTO responseDTO = new TicketResponseDTO();
        responseDTO.setTicketId(ticket.getTicketId());
        responseDTO.setVehicle(vehicleMapper.toDTO(ticket.getVehicle(), vehicleType));
        responseDTO.setStartTime(ticket.getStartTime());
        responseDTO.setEndTime(ticket.getEndTime());

        return responseDTO;
    }

    public TicketEntity toEntityModel(Ticket ticket) {

        VehicleEntity vehicleEntity = vehicleMapper.toEntity(ticket.getVehicle(), ticket.getVehicle().getClass());

        return new TicketEntity(vehicleEntity);
    }

    public Ticket toDomainModel(TicketEntity ticketEntity, VehicleType vehicleType) {
        Vehicle vehicle = vehicleMapper.toDomainModel(ticketEntity.getVehicle(), VehicleFactory.vehicleTypeFactory.get(vehicleType));
        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setTicketId(ticketEntity.getTicketId());
        ticket.setStartTime(ticketEntity.getStartTime());
        ticket.setEndTime(ticketEntity.getEndTime());

        return ticket;
    }
}
