package dev.victormoraes.adapters.mappers;

import dev.victormoraes.adapters.in.dtos.VehicleDTO;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleFactory;
import dev.victormoraes.domain.vehicle.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {


    VehicleDTO toDTO(Vehicle vehicle, VehicleType vehicleType) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setCode(vehicle.getCode());
        vehicleDTO.setColor(vehicle.getColor());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setType(vehicleType);

        return vehicleDTO;
    }

    public VehicleEntity toEntity(Vehicle vehicle, Class<?> vehicleType) {

        var vehicleEntity = VehicleFactory.getVehicleEntityByDomain(vehicleType);
        vehicleEntity.setCode(vehicle.getCode());
        vehicleEntity.setColor(vehicle.getColor());
        vehicleEntity.setModel(vehicle.getModel());
        return vehicleEntity;
    }

    public Vehicle toDomainModel(VehicleEntity vehicleEntity, Class<?> vehicleType) {
        Vehicle vehicle = VehicleFactory.getVehicle(vehicleType);
        vehicle.setModel(vehicleEntity.getModel());
        vehicle.setCode(vehicleEntity.getCode());
        vehicle.setColor(vehicleEntity.getColor());
        return vehicle;
    }

}
