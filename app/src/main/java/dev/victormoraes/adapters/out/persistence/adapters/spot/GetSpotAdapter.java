package dev.victormoraes.adapters.out.persistence;

import dev.victormoraes.adapters.mappers.SpotMapper;
import dev.victormoraes.adapters.out.persistence.repositories.SpotRepository;
import dev.victormoraes.domain.Spot;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.GetSpotPort;
import org.springframework.stereotype.Component;

@Component
public class GetSpotAdapter implements GetSpotPort {

    private final SpotRepository spotRepository;

    public GetSpotAdapter(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public Result<Spot> getSpot(Long spotId) {

        return spotRepository.findById(spotId)
                .map(SpotMapper::toDomain)
                .map(spot -> new Result<>(true, spot))
                .orElse(new Result<>(false, "Spot not found"));
    }
}