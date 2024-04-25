package dev.victormoraes.usecases;

import dev.victormoraes.domain.result.Result;
import dev.victormoraes.usecases.ports.DeleteSpotPort;
import org.springframework.stereotype.Component;

@Component
public class DeletingSpotUseCase {
    private final DeleteSpotPort deleteSpotPort;

    public DeletingSpotUseCase(DeleteSpotPort deleteSpotPort) {
        this.deleteSpotPort = deleteSpotPort;
    }

    public Result<Void> deleteSpot(Long spotId) {
        return deleteSpotPort.deleteSpot(spotId);
    }
}