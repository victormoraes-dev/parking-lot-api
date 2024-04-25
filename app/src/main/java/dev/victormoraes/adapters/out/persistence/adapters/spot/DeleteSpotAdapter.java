package dev.victormoraes.adapters.out.persistence.adapters.spot;

import dev.victormoraes.adapters.out.persistence.repositories.SpotRepository;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.DeleteSpotPort;
import org.springframework.stereotype.Component;

@Component
public class DeleteSpotAdapter implements DeleteSpotPort {
    private final SpotRepository spotRepository;

    public DeleteSpotAdapter(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public Result<Void> deleteSpot(Long spotId) {
        try {
            spotRepository.deleteById(spotId);
            return new Result<>(true);
        } catch (Exception e) {
            Result<Void> resultError = new Result<>(false);
            resultError.setErrorMessage(e.getMessage());
            return resultError;
        }
    }
}