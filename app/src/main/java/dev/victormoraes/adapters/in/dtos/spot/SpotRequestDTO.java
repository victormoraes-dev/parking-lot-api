package dev.victormoraes.adapters.in.dtos.spot;

import dev.victormoraes.domain.vehicle.VehicleType;

public class SpotRequestDTO extends SpotDTO{

    public SpotRequestDTO(int floor, String position, boolean isFree, VehicleType vehicleType) {
        super(floor, position, isFree, vehicleType);
    }
}
