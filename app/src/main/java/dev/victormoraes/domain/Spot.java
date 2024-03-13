package dev.victormoraes.domain;

import dev.victormoraes.domain.vehicle.VehicleType;

public class Spot {

    private Long spotId;
    private final int floor;
    private final String position;
    private boolean isFree;

    private final VehicleType vehicleType;

    public Spot(Long spotId, int floor, String position, boolean isFree, VehicleType vehicleType) {
        this.spotId = spotId;
        this.floor = floor;
        this.position = position;
        this.isFree = isFree;
        this.vehicleType = vehicleType;
    }

    public Spot(int floor, String position, boolean isFree, VehicleType vehicleType) {
        this.floor = floor;
        this.position = position;
        this.isFree = isFree;
        this.vehicleType = vehicleType;
    }

    public Long getSpotId() {
        return spotId;
    }

    public void setSpotId(Long spotId) {
        this.spotId = spotId;
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

    public int getFloor() {
        return floor;
    }
}
