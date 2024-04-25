package dev.victormoraes.usecases;

import dev.victormoraes.domain.Spot;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.UpdateSpotPort;
import org.springframework.stereotype.Component;

@Component
public class UpdatingSpotUseCase {

    private final UpdateSpotPort updateSpotPort;

    public UpdatingSpotUseCase(UpdateSpotPort updateSpotPort) {
        this.updateSpotPort = updateSpotPort;
    }

    public Result<Spot> updateSpot(Long spotId, Spot spot) {
        var updatedSpot = updateSpotPort.updateSpot(spotId, spot);
        var result = new Result<Spot>(true);
        result.setResult(updatedSpot);
        return result;
    }
}