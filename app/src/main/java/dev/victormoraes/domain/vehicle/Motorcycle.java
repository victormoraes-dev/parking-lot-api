package dev.victormoraes.domain.vehicle;

public class Motorcycle extends Vehicle{
    public Motorcycle(Long vehicleId, String plate, String model, String color) {
        super(vehicleId, plate, model, color);
    }

    public Motorcycle() {
        super();
    }
}
