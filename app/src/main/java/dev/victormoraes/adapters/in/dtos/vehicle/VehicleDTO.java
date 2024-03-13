package dev.victormoraes.adapters.in.dtos.vehicle;

import dev.victormoraes.domain.vehicle.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehicleDTO {

    @NotBlank(message = "Plate is mandatory")
    private String plate;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotBlank(message = "Color is mandatory")
    private String color;

    @NotNull(message = "Vehicle type is mandatory")
    private VehicleType type;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
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
