package dev.victormoraes.adapters.in.dtos.spot;

import dev.victormoraes.domain.vehicle.VehicleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public abstract class SpotDTO {

    @NotNull(message = "Floor field is required")
    @Min(0)
    private final Integer floor;

    @NotBlank(message = "Position field is required")
    private final String position;

    @NotNull(message = "Position field is required")
    private Boolean isFree;

    private final VehicleType vehicleType;

    public SpotDTO(int floor, String position, boolean isFree, VehicleType vehicleType) {
        this.floor = floor;
        this.position = position;
        this.isFree = isFree;
        this.vehicleType = vehicleType;
    }

    public int getFloor() {
        return floor;
    }

    public String getPosition() {
        return position;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
