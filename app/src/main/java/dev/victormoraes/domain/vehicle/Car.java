package dev.victormoraes.domain.vehicle;

public class Car extends Vehicle{

    public Car(Long vehicleId, String plate, String model, String color) {
        super(vehicleId, plate, model, color);
    }

    public Car() {
        super();
    }
}
