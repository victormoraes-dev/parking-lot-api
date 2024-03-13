package dev.victormoraes.adapters.in.dtos.spot;

import dev.victormoraes.domain.vehicle.VehicleType;

public abstract class SpotDTO {
    private final int floor;
    private final String position;
    private boolean isFree;

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
