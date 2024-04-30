package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.mappers.VehicleMapper;
import dev.victormoraes.adapters.out.persistence.entities.VehicleEntity;
import dev.victormoraes.adapters.out.persistence.repositories.VehicleRepository;
import dev.victormoraes.domain.vehicle.Vehicle;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.ports.GetVehiclePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetVehicleAdapter implements GetVehiclePort {

    private final VehicleRepository vehicleRepository;

    public GetVehicleAdapter(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<Vehicle> getVehicle(String code, VehicleType vehicleType) {

        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findByPlate(code);
        return vehicleEntity.map(VehicleEntity::toDomain);
    }
}
