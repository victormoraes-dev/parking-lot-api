package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.VehicleMapper;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.adapters.out.persistence.repositories.VehicleRepository;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.usecases.ports.CreateVehiclePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateVehicleAdapter implements CreateVehiclePort {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public CreateVehicleAdapter(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }


    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        var vehicleEntity = vehicleMapper.toEntity(vehicle, vehicle.getClass());

        VehicleEntity saved = vehicleRepository.save(vehicleEntity);

        return vehicleMapper.toDomainModel(saved, vehicle.getClass());
    }
}
