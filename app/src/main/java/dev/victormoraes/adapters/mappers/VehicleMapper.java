package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.in.dtos.vehicle.VehicleDTO;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleFactory;
import dev.victormoraes.domain.vehicle.VehicleType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VehicleMapper {


    public static VehicleDTO toDTO(Vehicle vehicle) {

        VehicleType vehicleType = getVehicleType(vehicle.getClass());

        return new VehicleDTO(vehicle.getPlate(), vehicle.getModel(), vehicle.getColor(), vehicleType);
    }

    public static VehicleEntity toEntity(Vehicle vehicle, Class<?> vehicleType) {

        var vehicleEntity = VehicleFactory.getVehicleEntityByDomain(vehicleType);
        vehicleEntity.setPlate(vehicle.getPlate());
        vehicleEntity.setColor(vehicle.getColor());
        vehicleEntity.setModel(vehicle.getModel());
        return vehicleEntity;
    }

    private static VehicleType getVehicleType(Class<?> vehicleClass) {
        return VehicleFactory.vehicleTypeFactory.entrySet().stream()
                .filter(entry -> entry.getValue().equals(vehicleClass))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown vehicle class: " + vehicleClass));
    }
}
