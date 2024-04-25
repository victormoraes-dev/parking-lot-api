package dev.victormoraes.usecases;

import dev.victormoraes.domain.Spot;
import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.GetSpotPort;
import org.springframework.stereotype.Component;

@Component
public class GettingSpotUseCase {

    private final GetSpotPort getSpotPort;

    public GettingSpotUseCase(GetSpotPort getSpotPort) {
        this.getSpotPort = getSpotPort;
    }

    public Result<Spot> getSpot(Long spotId) {
        return getSpotPort.getSpot(spotId);
    }
}