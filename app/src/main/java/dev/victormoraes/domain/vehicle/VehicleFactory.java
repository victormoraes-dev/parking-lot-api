package dev.victormoraes.domain.vehicle;

import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;

import java.util.Map;
import java.util.function.Supplier;

public class VehicleFactory {

    private static final Map<Class<?>, Supplier<Vehicle>> vehicleFactory = Map.of(
            Car.class, Car::new,
            Motorcycle.class, Motorcycle::new,
            Truck.class, Truck::new,
            Van.class, Van::new
    );

    public static final Map<VehicleType, Class<?>> vehicleTypeFactory = Map.of(
            VehicleType.CAR, Car.class,
            VehicleType.MOTORCYCLE, Motorcycle.class,
            VehicleType.TRUCK, Truck.class,
            VehicleType.VAN, Van.class);

    private static final Map<Class<?>, Supplier<VehicleEntity>> vehicleEntityByDomainFactory = Map.of(
            Car.class, VehicleEntity.CarEntity::new,
            Motorcycle.class, VehicleEntity.MotorcycleEntity::new,
            Truck.class, VehicleEntity.TruckEntity::new,
            Van.class, VehicleEntity.VanEntity::new
    );

    public static Vehicle getVehicle(Class<?> vehicleType) {

        Supplier<Vehicle> factory = vehicleFactory.get(vehicleType);

        if (factory != null) {
            return factory.get();
        } else {
            throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
        }
    }

    public static VehicleEntity getVehicleEntityByDomain(Class<?> vehicleType) {

        Supplier<VehicleEntity> factory = vehicleEntityByDomainFactory.get(vehicleType);

        if (factory != null) {
            return factory.get();
        } else {
            throw new IllegalArgumentException("Invalid vehicle entity type: " + vehicleType);
        }
    }
}
