package dev.victormoraes.adapters.in.dtos.vehicle;

import dev.victormoraes.domain.vehicle.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleDTO(@NotBlank(message = "Plate is mandatory") String plate,
                         @NotBlank(message = "Model is mandatory") String model,
                         @NotBlank(message = "Color is mandatory") String color,
                         @NotNull(message = "Vehicle type is mandatory") VehicleType type) {

    public VehicleDTO(String plate, String model, String color, VehicleType type) {
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.type = type;
    }

    @Override
    public String plate() {
        return plate;
    }

    @Override
    public String model() {
        return model;
    }

    @Override
    public String color() {
        return color;
    }

    @Override
    public VehicleType type() {
        return type;
    }
}
