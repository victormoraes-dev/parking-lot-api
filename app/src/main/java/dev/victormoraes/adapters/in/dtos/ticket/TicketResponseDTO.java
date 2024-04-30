package dev.victormoraes.adapters.in.dtos.ticket;

import dev.victormoraes.adapters.in.dtos.vehicle.VehicleDTO;

import java.time.LocalDateTime;

public record TicketResponseDTO(Long ticketId, VehicleDTO vehicle, LocalDateTime startTime, LocalDateTime endTime) {

}
