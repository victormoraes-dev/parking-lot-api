package dev.victormoraes.domain.vehicle;

public class Van extends Vehicle{
    public Van(Long vehicleId, String plate, String model, String color) {
        super(vehicleId, plate, model, color);
    }

    public Van() {
        super();
    }
}
