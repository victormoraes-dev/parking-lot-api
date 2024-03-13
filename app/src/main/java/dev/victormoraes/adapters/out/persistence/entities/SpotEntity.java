package dev.victormoraes.adapters.out.persistence.entities;

import dev.victormoraes.domain.vehicle.VehicleType;
import jakarta.persistence.*;

@Entity
@Table(name = "spots")
public class SpotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    @Column(nullable = false)
    private int floor;
    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private boolean isFree;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public SpotEntity(int floor, String position, boolean isFree, VehicleType vehicleType) {
        this.floor = floor;
        this.position = position;
        this.isFree = isFree;
        this.vehicleType = vehicleType;
    }

    public SpotEntity() {
    }

    public Long getSpotId() {
        return spotId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
