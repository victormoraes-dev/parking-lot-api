package dev.victormoraes.adapters.in.dtos.ticket;

import dev.victormoraes.adapters.in.dtos.vehicle.VehicleDTO;

public record TicketRequestDTO(VehicleDTO vehicle, String username) {
}
