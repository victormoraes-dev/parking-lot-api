package dev.victormoraes.adapters.out.persistence.entities;

import dev.victormoraes.domain.vehicle.*;
import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column
    private String plate;

    @Column
    private String model;

    @Column
    private String color;

    public VehicleEntity() {
    }

    public VehicleEntity(String plate, String model, String color) {
        this.plate = plate;
        this.model = model;
        this.color = color;
    }

    public abstract Vehicle toDomain();

    @Entity
    @DiscriminatorValue("CAR")
    public static class CarEntity extends VehicleEntity {

        @Override
        public Vehicle toDomain() {
            return new Car(this.getVehicleId(), this.getPlate(), this.getModel(), this.getColor());
        }
    }

    @Entity
    @DiscriminatorValue("MOTORCYCLE")
    public static class MotorcycleEntity extends VehicleEntity {
        @Override
        public Vehicle toDomain() {
            return new Motorcycle(this.getVehicleId(), this.getPlate(), this.getModel(), this.getColor());
        }
    }

    @Entity
    @DiscriminatorValue("TRUCK")
    public static class TruckEntity extends VehicleEntity {
        @Override
        public Vehicle toDomain() {
            return new Truck(this.getVehicleId(), this.getPlate(), this.getModel(), this.getColor());
        }
    }

    @Entity
    @DiscriminatorValue("VAN")
    public static class VanEntity extends VehicleEntity {
        @Override
        public Vehicle toDomain() {
            return new Van(this.getVehicleId(), this.getPlate(), this.getModel(), this.getColor());
        }
    }

    public Long getVehicleId() {
        return vehicleId;
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
