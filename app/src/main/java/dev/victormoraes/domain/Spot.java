package dev.victormoraes.domain;

import dev.victormoraes.domain.vehicle.VehicleType;

public class Spot {

    private final String position;
    private boolean isFree;

    private final VehicleType[] allowedVehicleType;

    public Spot(String position, VehicleType[] allowedVehicleType) {
        this.position = position;
        this.allowedVehicleType = allowedVehicleType;
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

    public VehicleType[] getAllowedVehicleType() {
        return allowedVehicleType;
    }
}
