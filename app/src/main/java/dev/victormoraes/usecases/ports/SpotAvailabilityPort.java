package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.vehicle.VehicleType;

public interface SpotAvailabilityPort {

    boolean checkSpotAvailability(VehicleType vehicleType);
}
