package dev.victormoraes.adapters.out.persistence.adapters.spot;

import dev.victormoraes.adapters.mappers.SpotMapper;
import dev.victormoraes.adapters.out.persistence.entities.SpotEntity;
import dev.victormoraes.adapters.out.persistence.repositories.SpotRepository;
import dev.victormoraes.domain.Spot;
import dev.victormoraes.usecases.ports.CreateSpotPort;
import org.springframework.stereotype.Component;

@Component
public class CreateSpotAdapter implements CreateSpotPort {

    private final SpotRepository spotRepository;

    public CreateSpotAdapter(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public Spot createSpot(Spot spot) {

        SpotEntity entity = SpotMapper.toEntity(spot);
        var savedEntity = spotRepository.save(entity);
        return SpotMapper.toDomain(savedEntity);
    }
}
