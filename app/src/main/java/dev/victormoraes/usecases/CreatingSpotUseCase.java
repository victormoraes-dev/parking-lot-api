package dev.victormoraes.usecases;

import dev.victormoraes.domain.Spot;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.CreateSpotPort;
import org.springframework.stereotype.Component;

@Component
public class CreatingSpotUseCase {

    private final CreateSpotPort createSpotPort;

    public CreatingSpotUseCase(CreateSpotPort createSpotPort) {
        this.createSpotPort = createSpotPort;
    }

    public Result<Spot> createSpot(Spot spot) {

        var createdSpot = createSpotPort.createSpot(spot);
        var result = new Result<Spot>(true);
        result.setResult(createdSpot);
        return result;
    }
}
