package dev.victormoraes.adapters.in.dtos.spot;

import dev.victormoraes.domain.vehicle.VehicleType;

public class SpotResponseDTO extends SpotDTO {

    private final Long spotId;

    public SpotResponseDTO(Long spotId, int floor, String position, boolean isFree, VehicleType vehicleType) {
        super(floor, position, isFree, vehicleType);
        this.spotId = spotId;
    }

    public Long getSpotId() {
        return spotId;
    }
}
