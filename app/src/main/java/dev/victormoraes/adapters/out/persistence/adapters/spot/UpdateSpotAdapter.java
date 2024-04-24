package dev.victormoraes.adapters.out.persistence.adapters.spot;

import dev.victormoraes.adapters.mappers.SpotMapper;
import dev.victormoraes.adapters.out.persistence.entities.SpotEntity;
import dev.victormoraes.adapters.out.persistence.repositories.SpotRepository;
import dev.victormoraes.domain.Spot;
import dev.victormoraes.usecases.ports.UpdateSpotPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateSpotAdapter implements UpdateSpotPort {

    private final SpotRepository spotRepository;

    public UpdateSpotAdapter(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public Spot updateSpot(Long spotId, Spot spot) {
        Optional<SpotEntity> existingEntityOptional = spotRepository.findById(spotId);

        if (existingEntityOptional.isPresent()) {
            SpotEntity existingEntity = existingEntityOptional.get();
            existingEntity.setVehicleType(spot.getVehicleType());
            existingEntity.setFloor(spot.getFloor());
            existingEntity.setPosition(spot.getPosition());
            existingEntity.setFree(spot.isFree());

            SpotEntity savedEntity = spotRepository.save(existingEntity);
            return SpotMapper.toDomain(savedEntity);
        } else {
            throw new IllegalArgumentException("Spot not found with id: " + spotId);
        }
    }
}
