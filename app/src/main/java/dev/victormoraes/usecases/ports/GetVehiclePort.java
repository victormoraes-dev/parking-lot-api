package dev.victormoraes.usecases.ports;

import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleType;

import java.util.Optional;

public interface GetVehiclePort {

    Optional<Vehicle> getVehicle(String code, VehicleType vehicleType);
}
