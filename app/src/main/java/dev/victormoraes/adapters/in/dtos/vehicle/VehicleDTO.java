package dev.victormoraes.adapters.in.dtos.vehicle;

import dev.victormoraes.domain.vehicle.VehicleType;

public class VehicleDTO {

    private String code;
    private String model;
    private String color;

    private VehicleType type;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
