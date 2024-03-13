package dev.victormoraes.adapters.in.dtos.ticket;

import dev.victormoraes.adapters.in.dtos.vehicle.VehicleDTO;
import jakarta.validation.Valid;

public record TicketRequestDTO(@Valid VehicleDTO vehicle, String username) {
}
