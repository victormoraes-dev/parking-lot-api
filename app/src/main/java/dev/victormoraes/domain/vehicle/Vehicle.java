package dev.victormoraes.domain.vehicle;

public abstract class Vehicle {

    private Long vehicleId;
    private String plate;
    private String model;
    private String color;

    public Vehicle(Long vehicleId, String plate, String model, String color) {
        this.vehicleId = vehicleId;
        this.plate = plate;
        this.model = model;
        this.color = color;
    }

    public Vehicle() {
    }

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

}
