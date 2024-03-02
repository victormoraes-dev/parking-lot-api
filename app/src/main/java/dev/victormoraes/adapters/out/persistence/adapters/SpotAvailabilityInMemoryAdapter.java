package dev.victormoraes.adapters.out.persistence.adapters;

import dev.victormoraes.adapters.out.persistence.repositories.SpotRepository;
import dev.victormoraes.domain.vehicle.VehicleType;
import dev.victormoraes.usecases.ports.SpotAvailabilityPort;
import org.springframework.stereotype.Component;

@Component
public class SpotAvailabilityInMemoryAdapter implements SpotAvailabilityPort {

    private final SpotRepository repository;

    public SpotAvailabilityInMemoryAdapter(SpotRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean checkSpotAvailability(VehicleType vehicleType) {

        return !repository.findByAllowedVehicleTypeAndIsFree(vehicleType).isEmpty();
    }
}
